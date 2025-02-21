package clbisk.simplestbudget.ui.reusable.budgetcategories.list

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

data class BudgetCategoryState(
	val category: BudgetCategory? = null,
	val transactionTotal: Float? = null,
)
