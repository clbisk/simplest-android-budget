package clbisk.simplest_budget.ui.budgetCategories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import clbisk.simplest_budget.data.budgetCategory.BudgetCategoriesRepository

class CategoryInputViewModel(
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
