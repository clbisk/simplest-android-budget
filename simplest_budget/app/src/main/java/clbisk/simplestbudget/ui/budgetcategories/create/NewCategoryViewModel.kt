package clbisk.simplestbudget.ui.budgetcategories.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.ui.budgetcategories.BudgetCategoryInput
import clbisk.simplestbudget.ui.budgetcategories.BudgetCategoryInputState
import clbisk.simplestbudget.ui.budgetcategories.toBudgetCategory

class NewCategoryViewModel(
	private val categoryRepository: BudgetCategoriesRepository
) : ViewModel() {
	var categoryInputState by mutableStateOf(BudgetCategoryInputState())
		private set

	fun updateInput(input: BudgetCategoryInput) {
		categoryInputState =
			BudgetCategoryInputState(category = input, isEntryValid = validateInput(input))
	}

	private fun validateInput(
		uiState: BudgetCategoryInput = categoryInputState.category
	): Boolean {
		return with(uiState) {
			name.isNotBlank()
		}
	}

	suspend fun saveItem() {
		if (validateInput()) {
			categoryRepository.insert(categoryInputState.category.toBudgetCategory())
		}
	}
}
