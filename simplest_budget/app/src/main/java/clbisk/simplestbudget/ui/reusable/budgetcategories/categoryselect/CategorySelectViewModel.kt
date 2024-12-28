package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.reusable.budgetcategories.list.CategoryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategorySelectViewModel @Inject constructor(
	private val categoriesRepo: BudgetCategoriesRepository,
) : ViewModel() {
	val inputState: MutableStateFlow<String> = MutableStateFlow("")
	val inputError: MutableStateFlow<Boolean> = MutableStateFlow(false)

	private val categoryListState: StateFlow<CategoryListState> =
		categoriesRepo.getAllCategories().map { CategoryListState(it) }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(),
				initialValue = CategoryListState()
			)

	private val categoryList: List<BudgetCategory>? = categoryListState.value.categoryList
	val filteredList = categoryList?.filter { it.categoryName.contains(inputState.value) } ?: listOf()

	private fun validateUpdate(newInput: String): Boolean {
		return inputState.value.isNotEmpty()
	}

	fun onUpdate(newInput: String) {
		if (validateUpdate(newInput)) {
			inputError.value = false
			inputState.value = newInput
		}
	}

	fun validateInput(): Boolean {
		return categoryListState.value.categoryList!!.any { it.categoryName == inputState.value }
	}

	fun onUnfocus() {
		if (!validateInput()) {
			inputError.value = true
			inputState.value = ""
		} else
			inputError.value = false
	}
}
