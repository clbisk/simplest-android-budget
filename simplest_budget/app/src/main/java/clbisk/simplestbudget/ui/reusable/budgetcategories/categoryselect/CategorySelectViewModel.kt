package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class CategoryListState(
	val categoryList: List<BudgetCategory>? = null,
	val filteredList: List<BudgetCategory>? = null,
)

@HiltViewModel
class CategorySelectViewModel @Inject constructor(
	private val categoriesRepo: BudgetCategoriesRepository,
) : ViewModel() {
	val inputState: MutableStateFlow<String> = MutableStateFlow("")
	val inputError: MutableStateFlow<Boolean> = MutableStateFlow(false)

	val categoryListState: StateFlow<CategoryListState> =
		categoriesRepo.getAllCategories().map {
			CategoryListState(it, it.filter { cat -> cat.categoryName.contains(inputState.value) })
		}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(),
				initialValue = CategoryListState()
			)

	fun onUpdate(newInput: String) {
		inputState.value = newInput
		if (newInput.isNotEmpty()) {
			inputError.value = false
		}
	}

	private fun validateInput(): Boolean {
		return categoryListState.value.categoryList?.any {
			it.categoryName == inputState.value
		} ?: false
	}

	fun onUnfocus() {
		if (!validateInput()) {
			inputError.value = true
			inputState.value = ""
		} else
			inputError.value = false
	}
}
