package clbisk.simplestbudget.ui.reusable.budgetcategories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * retrieves the items in the Room database
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(
	budgetCategoriesRepository: BudgetCategoriesRepository,
	transactionsRepository: TransactionRecordsRepository,
) : ViewModel() {
	val categoryListState: StateFlow<SummedCategoryListState> =
		budgetCategoriesRepository.getAllCategories().map { allCategories ->
			val categoryStates = allCategories.map {
				val transactionTotal =
					transactionsRepository.getTransactionTotalForCategory(it.id).first()
				BudgetCategoryState(it, transactionTotal)
			}

			SummedCategoryListState(categoryStates)
		}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = SummedCategoryListState()
			)

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}
