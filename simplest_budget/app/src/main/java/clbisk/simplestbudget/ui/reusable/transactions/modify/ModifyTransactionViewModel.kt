package clbisk.simplestbudget.ui.reusable.transactions.modify

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.nav.args.NavArgs
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyFloat
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ModifyTransactionState(
	val loading: Boolean = true,
	val savedTransaction: TransactionRecord? = null,
	val input: TransactionInput = TransactionInput(),
)

@HiltViewModel
class ModifyTransactionViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val transactionRepo: TransactionRecordsRepository,
	private val categoriesRepo: BudgetCategoriesRepository,
	private val widgetRepo: WidgetModelRepository,
) : ViewModel() {
	/** read navigation input args */
	private val maybeTransactionArg: String? = savedStateHandle[NavArgs.TRANSACTION_ID.name]
	private val maybeTransactionId = maybeTransactionArg?.toInt()
	private val maybeCategoryArg: String? = savedStateHandle[NavArgs.CAT_ID.name]
	private val maybeCategoryId = maybeCategoryArg?.toInt()

	val inputState = MutableStateFlow(ModifyTransactionState())

	/** load transaction from db if transaction id was passed, or cat name if cat id passed **/
	init {
		viewModelScope.launch {
			if (maybeTransactionId != null) {
				val loadedTransaction =
					transactionRepo.getTransaction(maybeTransactionId)
						.filterNotNull()
						.first()

				val categoryName = getCategoryNameForId(loadedTransaction.inCategoryId)

				inputState.update {
					it.copy(
						savedTransaction = loadedTransaction,
						input = loadedTransaction.toTransactionInput(categoryName),
					)
				}
			}

			if (maybeCategoryId !== null) {
				val categoryName = getCategoryNameForId(maybeCategoryId)
				inputState.update { it.copy(
					input = TransactionInput(
						inCategoryId = maybeCategoryId,
						inCategoryName = categoryName,
					),
				) }
			}

			inputState.update { it.copy(loading = false) }
		}
	}

	private suspend fun getCategoryNameForId(catId: Int): String {
		return categoriesRepo.getCategory(catId).first().categoryName
	}

	private fun validateUpdate(newInput: TransactionInput): Boolean {
		return parseStringAsCurrencyFloat(newInput.currencyAmount) != null
	}

	private fun onUpdate(newInput: TransactionInput) {
		if (validateUpdate(newInput)) {
			inputState.update {
				it.copy(input = newInput)
			}
		}
	}

	fun updateCategory(catId: Int, catName: String) {
		onUpdate(inputState.value.input.copy(inCategoryId = catId, inCategoryName = catName))
	}
	fun updateAmount(newAmt: String) { onUpdate(inputState.value.input.copy(currencyAmount = newAmt)) }
	fun updateDescription(newTxt: String) { onUpdate(inputState.value.input.copy(description = newTxt)) }

	private fun validateInput(newInput: TransactionInput): Boolean {
		return newInput.inCategoryName !== null && newInput.inCategoryName.isNotBlank()
				&& newInput.currencyAmount.isNotBlank()
	}

	suspend fun saveTransaction() {
		val newTransactionInput = inputState.value.input

		if (validateInput(newTransactionInput)) {
			val originalTransactionRecord = inputState.value.savedTransaction
			val newTransactionRecord = newTransactionInput.toTransactionRecord()

			if (originalTransactionRecord == null) {
				// insert new transaction
				transactionRepo.insert(newTransactionRecord)
			} else {
				// update existing transaction
				transactionRepo.update(newTransactionRecord)
			}

			// update widget for transaction input's category
			updateWidget(newTransactionInput.inCategoryId!!, newTransactionInput.inCategoryName!!)
		}
	}

	private suspend fun updateWidget(forCategoryId: Int, forCategoryName: String) {
		val newTransactionTotal = transactionRepo
			.getTransactionTotalForCategory(forCategoryId)
			.first()

		widgetRepo.updateTransactionTotalForCategory(
			categoryId = forCategoryId,
			categoryName = forCategoryName,
			newTotal = newTransactionTotal,
		)
	}
}
