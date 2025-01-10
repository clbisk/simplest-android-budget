package clbisk.simplestbudget.ui.screens.transactions.list.forcategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.nav.args.NavArgs
import clbisk.simplestbudget.ui.reusable.model.TransactionListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TransactionsForCategoryViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	categoryRepo: BudgetCategoriesRepository,
	transactionsRepo: TransactionRecordsRepository,
) : ViewModel() {
	/** read category id to be loaded from navigation input */
	private val catArg: String = checkNotNull(savedStateHandle[NavArgs.CAT_ID.name])
	val catId = catArg.toInt()

	/** get category details to display over transaction list */
	val categoryData: StateFlow<BudgetCategory> =
		categoryRepo.getCategory(catId).stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
			initialValue = BudgetCategory(
				id = catId,
				categoryName = "",
				spendingLimit = 0f,
			),
		)

	val transactionsTotalState: StateFlow<Float?> =
		transactionsRepo.getTransactionTotalForCategory(catId).stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
			initialValue = null,
		)

	val transactionsListState: StateFlow<TransactionListState> =
		transactionsRepo.getTransactionsForCategory(catId).map { TransactionListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = TransactionListState(),
			)

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}
