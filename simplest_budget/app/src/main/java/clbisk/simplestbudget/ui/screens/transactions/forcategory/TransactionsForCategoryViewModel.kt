package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.reusable.model.CategoryState
import clbisk.simplestbudget.ui.reusable.model.TransactionListState
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

@HiltViewModel
class TransactionsForCategoryViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	categoryRepo: BudgetCategoriesRepository,
	transactionsRepo: TransactionRecordsRepository,
) : ViewModel() {
	/** read category name to be loaded from navigation input */
	val nameArg: String = checkNotNull(savedStateHandle["categoryName"])

	/** get category details to display over transaction list */
	val categoryState: MutableStateFlow<CategoryState> = MutableStateFlow(
		CategoryState(nameArg)
	)

	init {
		viewModelScope.launch {
			val categoryData = categoryRepo.getCategory(nameArg)
				.filterNotNull()
				.first()

			val transactionsTotal =
				transactionsRepo.getTransactionTotalForCategory(nameArg).first()

			categoryState.update {
				it.copy(
					loaded = true,
					data = categoryData
				)
			}
		}
	}

	val transactionsListState: StateFlow<TransactionListState> =
		transactionsRepo.getTransactionsForCategory(nameArg).map { TransactionListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = TransactionListState()
			)

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}
