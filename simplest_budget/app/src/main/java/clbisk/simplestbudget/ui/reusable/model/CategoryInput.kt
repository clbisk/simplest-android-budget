package clbisk.simplestbudget.ui.reusable.model

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

data class CategoryInput(
	val categoryId: Int = 0,
	val categoryName: String = "",
	val spendingLimit: Float = 0f,
)

/** helper translation fns */
fun CategoryInput.toBudgetCategory(): BudgetCategory {
	return BudgetCategory(
		id = this.categoryId,
		categoryName = this.categoryName,
		spendingLimit = this.spendingLimit,
	)
}

fun BudgetCategory.toCategoryInput(): CategoryInput {
	return CategoryInput(
		categoryId = this.id,
		categoryName = this.categoryName,
		spendingLimit = this.spendingLimit,
	)
}
