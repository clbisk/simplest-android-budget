package clbisk.simplestbudget.ui.reusable.transactions.modify

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyLong
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyTransactionViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val transactionRepo: TransactionRecordsRepository,
	private val widgetRepo: WidgetModelRepository,
) : ViewModel() {
	/** read navigation input args */
	private val maybeTransactionId: Int? = savedStateHandle["transactionId"]
	val maybeCategoryName: String? = savedStateHandle["categoryName"]

	val inputState: MutableStateFlow<TransactionInput?> = MutableStateFlow( null)

	/** load transaction from db if transaction id was passed **/
	init {
		viewModelScope.launch {
			if (maybeTransactionId != null) {
				val initialTransactionRecord =
					transactionRepo.getTransaction(maybeTransactionId).first()
				inputState.value = initialTransactionRecord.toTransactionInput()
			}
			else if (maybeCategoryName != null) {
				TransactionInput(inCategoryName = maybeCategoryName)
			}
			else {
				inputState.value = TransactionInput()
			}
		}
	}

	private fun validateUpdate(newInput: TransactionInput): Boolean {
		return parseStringAsCurrencyLong(newInput.currencyAmount) != null
	}

	fun onUpdate(newInput: TransactionInput) {
		if (validateUpdate(newInput)) {
			inputState.value = newInput
		}
	}

	private fun validateInput(): Boolean {
		val input = inputState.value!!
		return input.inCategoryName.isNotBlank() && input.currencyAmount.isNotBlank()
	}

	suspend fun saveTransaction() {
		val newTransactionInput = inputState.value!!
		val newTransactionRecord = newTransactionInput.toTransactionRecord()

		if (validateInput()) {
			if (newTransactionInput.id == null) {
				// insert new transaction
				transactionRepo.insert(newTransactionRecord)
			} else {
				// update existing transaction
				transactionRepo.update(newTransactionRecord)
			}
		}

		// update widget
		val forCategory = newTransactionInput.inCategoryName
		val newTransactionTotal = transactionRepo
			.getTransactionTotalForCategory(forCategory)
			.first()

		widgetRepo.updateTransactionTotalForCategory(
			categoryName = forCategory,
			newTotal = newTransactionTotal,
		)
	}
}
