package clbisk.simplestbudget.widget.newtransaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.nav.args.NavArgs
import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput
import clbisk.simplestbudget.ui.reusable.transactions.modify.toTransactionRecord
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyFloat
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WidgetAddTransactionState(
	val loading: Boolean = true,
	val savedTransaction: TransactionRecord? = null,
	val input: TransactionInput = TransactionInput(),
)

@HiltViewModel
class WidgetAddTransactionViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val transactionRepo: TransactionRecordsRepository,
	private val categoriesRepo: BudgetCategoriesRepository,
	private val widgetRepo: WidgetModelRepository,
) : ViewModel() {
	/** read navigation input args */
	private val maybeCategoryArg: String? = savedStateHandle[NavArgs.CAT_ID.name]
	private val maybeCategoryId = maybeCategoryArg?.toInt()

	val inputState: MutableStateFlow<WidgetAddTransactionState> = MutableStateFlow(
		WidgetAddTransactionState()
	)

	/** load transaction from db if transaction id was passed, or cat name if cat id passed **/
	init {
		viewModelScope.launch {
			if (maybeCategoryId !== null) {
				val categoryName = categoriesRepo.getCategory(maybeCategoryId).first().categoryName
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

	private fun validateUpdate(newInput: TransactionInput): Boolean {
		return parseStringAsCurrencyFloat(newInput.currencyAmount) != null
	}

	fun onUpdate(newInput: TransactionInput) {
		if (validateUpdate(newInput)) {
			inputState.update {
				it.copy(input = newInput)
			}
		}
	}

	fun updateCategory(newId: Int, newName: String) {
		onUpdate(inputState.value.input.copy(inCategoryId = newId, inCategoryName = newName))
	}

	private fun validateInput(newInput: TransactionInput): Boolean {
		return (!newInput.inCategoryName.isNullOrBlank()) && newInput.currencyAmount.isNotBlank()
	}

	suspend fun saveTransaction() {
		val newTransactionInput = inputState.value.input

		if (validateInput(newTransactionInput)) {
			val newTransactionRecord = newTransactionInput.toTransactionRecord()
			transactionRepo.insert(newTransactionRecord)

			// update widget for transaction input's category
			updateWidget(newTransactionInput.inCategoryId!!, newTransactionInput.inCategoryName!!)
		}
	}

	private suspend fun updateWidget(forCatId: Int, forCatName: String) {
		val newTransactionTotal = transactionRepo
			.getTransactionTotalForCategory(forCatId)
			.first()

		widgetRepo.updateTransactionTotalForCategory(
			categoryId = forCatId,
			categoryName = forCatName,
			newTotal = newTransactionTotal,
		)
	}
}
