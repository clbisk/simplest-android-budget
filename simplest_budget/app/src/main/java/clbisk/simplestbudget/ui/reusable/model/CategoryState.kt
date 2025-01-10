package clbisk.simplestbudget.ui.reusable.model

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

data class CategoryState(
	val categoryName: String,
	val loaded: Boolean = false,
	val data: BudgetCategory? = null,
	val transactionTotal: Float? = null,
)
