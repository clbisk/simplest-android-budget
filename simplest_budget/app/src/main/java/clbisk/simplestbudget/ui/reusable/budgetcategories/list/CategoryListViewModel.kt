package clbisk.simplestbudget.ui.reusable.budgetcategories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class CategoryListState(
	val categoryList: List<BudgetCategory>? = null,
	val filteredList: List<BudgetCategory>? = null,
)

/**
 * retrieves the items in the Room database
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(
	budgetCategoriesRepository: BudgetCategoriesRepository
) : ViewModel() {
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
