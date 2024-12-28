package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput
import clbisk.simplestbudget.ui.reusable.transactions.modify.toTransactionRecord
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyLong
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val transactionRepo: TransactionRecordsRepository,
	private val widgetRepo: WidgetModelRepository,
) : ViewModel() {
	/** read transaction id to be loaded from navigation input */
	private val categoryName: String = checkNotNull(savedStateHandle["categoryName"])

	val inputState: MutableStateFlow<TransactionEditState> = MutableStateFlow(
		TransactionEditState(
			stateLoaded = true, initialCategoryName = categoryName, input = TransactionInput()
		)
	)

	private fun validateUpdate(newInput: TransactionInput): Boolean {
		return parseStringAsCurrencyLong(newInput.currencyAmount) != null
	}

	fun onUpdate(newInput: TransactionInput) {
		if (validateUpdate(newInput)) {
			inputState.update {
				it.copy(input = newInput)
			}
		}
	}

	private fun validateInput(): Boolean {
		val input = inputState.value.input
		return input?.inCategoryName?.isNotBlank() ?: false
	}

	suspend fun saveNewCategory() {
		val editedTransaction = inputState.value.input

		if (validateInput()) {
			transactionRepo.insert(editedTransaction!!.toTransactionRecord())
		}

		// update widget
		val forCategory = editedTransaction!!.inCategoryName
		val newTransactionTotal = transactionRepo
			.getTransactionTotalForCategory(forCategory)
			.first()

		widgetRepo.updateTransactionTotalForCategory(
			categoryName = forCategory,
			newTotal = newTransactionTotal,
		)
	}
}
