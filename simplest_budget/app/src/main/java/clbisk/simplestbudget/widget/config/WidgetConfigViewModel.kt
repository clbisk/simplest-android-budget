package clbisk.simplestbudget.widget.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.budgetCategory.OfflineBudgetCategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class CategoryListState(
	val categoryList: List<BudgetCategory>? = null
)

/**
 * retrieves the items in the Room database
 */
@HiltViewModel
class WidgetConfigViewModel @Inject constructor(
	budgetCategoriesRepository: OfflineBudgetCategoriesRepository,
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
