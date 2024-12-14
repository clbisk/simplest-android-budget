package clbisk.simplestbudget.ui.budgetcategories.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository

class EditCategoryViewModel(
	private val categoryRepository: BudgetCategoriesRepository
) : ViewModel() {
	private var inputState = MutableLiveData(CategoryInput())

	fun updateInput(input: CategoryInput) {
		inputState.value = input
	}

	private fun validateInput(): Boolean {
		return !(inputState.value?.categoryName.isNullOrBlank())
	}

	suspend fun saveItem() {
		if (validateInput()) {
			inputState.getValue()?.let { categoryRepository.insert(it.toBudgetCategory()) }
		}
	}
}
