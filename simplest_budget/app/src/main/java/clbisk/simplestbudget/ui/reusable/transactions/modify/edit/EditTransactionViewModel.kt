package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput
import clbisk.simplestbudget.ui.reusable.transactions.modify.toTransactionInput
import clbisk.simplestbudget.ui.reusable.transactions.modify.toTransactionRecord
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyLong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTransactionViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val transactionRepo: TransactionRecordsRepository,
) : ViewModel() {
	/** read transaction id to be loaded from navigation input */
	private val idArg: String = checkNotNull(savedStateHandle["transactionId"])
	/** store original values of the specified category before edits */
	private lateinit var initialTransactionRecord: TransactionRecord

	val inputState: MutableStateFlow<TransactionEditState> = MutableStateFlow(TransactionEditState())

	init {
		viewModelScope.launch {
			initialTransactionRecord = transactionRepo.getTransaction(idArg.toInt())
				.filterNotNull()
				.first()

			inputState.update {
				it.copy(
					stateLoaded = true,
					input = initialTransactionRecord.toTransactionInput()
				)
			}
		}
	}

	private fun validateUpdate(newInput: TransactionInput?): Boolean {
		if (!inputState.value.stateLoaded || newInput == null) {
			throw Exception("cannot edit category before loading previous value")
		}

		return parseStringAsCurrencyLong(newInput.currencyAmount) != null
	}

	fun onUpdate(newInput: TransactionInput?) {
		if (validateUpdate(newInput)) {
			inputState.update {
				it.copy(input = newInput)
			}
		}
	}

	private fun validateInput(): Boolean {
		val input = inputState.value.input
			?: throw Exception("cannot edit category before loading previous value")

		return input.inCategoryName.isNotBlank()
	}

	suspend fun saveEditedCategory() {
		val editedTransaction = inputState.value.input
			?: throw Exception("cannot save null category input")

		if (validateInput()) {
			transactionRepo.update(editedTransaction.toTransactionRecord())
		}
	}
}
