package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SimpleCategorySelectViewModel @Inject constructor(
	private val categoriesRepo: BudgetCategoriesRepository,
) : ViewModel() {
	val categoryListState: StateFlow<CategoryListState> =
		categoriesRepo.getAllCategories().map { CategoryListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(),
				initialValue = CategoryListState()
			)
}
