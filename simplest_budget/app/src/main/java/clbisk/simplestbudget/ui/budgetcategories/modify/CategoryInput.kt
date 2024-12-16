package clbisk.simplestbudget.ui.budgetcategories.modify

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

data class CategoryInput(
	val categoryName: String = "",
	val spendingLimit: Int = 0
)

/** helper translation fns */
fun CategoryInput.toBudgetCategory(): BudgetCategory {
	return BudgetCategory(
		categoryName = this.categoryName,
		spendingLimit = this.spendingLimit,
	)
}

fun BudgetCategory.toCategoryInput(): CategoryInput {
	return CategoryInput(
		categoryName = this.categoryName,
		spendingLimit = this.spendingLimit,
	)
}
