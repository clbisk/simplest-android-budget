package clbisk.simplestbudget.ui.budgetcategories.edit

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

data class CategoryInput (
	val categoryName: String = "",
	val spendingLimit: Int = 0
) {}

fun CategoryInput.toBudgetCategory(): BudgetCategory {
	return BudgetCategory(
		categoryName = this.categoryName,
		spendingLimit = this.spendingLimit
	)
}
