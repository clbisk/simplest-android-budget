package clbisk.simplestbudget.ui.budgetcategories.modify.edit

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.budgetcategories.modify.CategoryInput
import clbisk.simplestbudget.ui.budgetcategories.modify.toCategoryInput

data class CategoryEditState(
	val stateLoaded: Boolean = false,
	val input: CategoryInput? = null,
)

/** helper translation fns */
fun CategoryEditState.toBudgetCategory() : BudgetCategory {
	if (this.input == null) {
		throw Exception("Tried to convert edit screen state to Category before ")
	}

	return BudgetCategory(
		categoryName = this.input.categoryName,
		spendingLimit = this.input.spendingLimit,
	)
}

fun BudgetCategory.toCategoryEditState() : CategoryEditState {
	return CategoryEditState(
		stateLoaded = true,
		input = this.toCategoryInput()
	)
}
