package clbisk.simplest_budget.ui.screens.home.budget_category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplest_budget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplest_budget.data.budgetCategory.BudgetCategory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class CategoryListState(
	val categoryList: List<BudgetCategory>? = null
)

/**
 * retrieves the items in the Room database
 */
class CategoryListViewModel(budgetCategoriesRepository: BudgetCategoriesRepository) : ViewModel() {
	val categoryListState: StateFlow<CategoryListState> =
		budgetCategoriesRepository.getAllCategories().map { CategoryListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
				initialValue = CategoryListState()
			)

	companion object {
		private const val TIMEOUT_MILLIS = 5_000L
	}
}
