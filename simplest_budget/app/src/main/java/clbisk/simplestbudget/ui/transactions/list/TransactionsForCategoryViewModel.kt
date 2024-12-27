package clbisk.simplestbudget.ui.transactions.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TransactionListState(
	val transactionList: List<TransactionRecord>? = null
)

data class CategoryState(
	val categoryName: String,
	val loaded: Boolean = false,
	val data: BudgetCategory? = null
)

@HiltViewModel
class TransactionsForCategoryViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	categoryRepository: BudgetCategoriesRepository,
	transactionRecordsRepository: TransactionRecordsRepository,
) : ViewModel() {
	/** read category name to be loaded from navigation input */
	private val nameArg: String = checkNotNull(savedStateHandle["categoryName"])

	/** get category details to display over transaction list */
	val categoryState: MutableStateFlow<CategoryState> = MutableStateFlow(
		CategoryState(nameArg)
	)

	init {
		viewModelScope.launch {
			val categoryData = categoryRepository.getCategory(nameArg)
				.filterNotNull()
				.first()

			categoryState.update {
				it.copy(
					loaded = true,
					data = categoryData
				)
			}
		}
	}

	val transactionsListState: StateFlow<TransactionListState> =
		transactionRecordsRepository.getTransactionsForCategory(nameArg).map { TransactionListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = TransactionListState()
			)

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}
