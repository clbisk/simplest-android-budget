package clbisk.simplestbudget.ui.budgetcategories

import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

/** ui state for a single budget category */
data class BudgetCategoryInput(
	val name: String = "",
	val spendingLimit: Int = 0,
)

data class BudgetCategoryInputState(
	val category: BudgetCategoryInput = BudgetCategoryInput(),
	val isEntryValid: Boolean = false,
)

/** conversion bw ui and db budget category objects */
fun BudgetCategoryInput.toBudgetCategory(): BudgetCategory = BudgetCategory(
	categoryName = name,
	spendingLimit = spendingLimit,
)

fun BudgetCategory.toBudgetCategoryInput(): BudgetCategoryInput = BudgetCategoryInput(
	name = categoryName,
	spendingLimit = spendingLimit,
)

fun BudgetCategory.toBudgetCategoryInputState(isEntryValid: Boolean = false): BudgetCategoryInputState = BudgetCategoryInputState(
	category = this.toBudgetCategoryInput(),
	isEntryValid = isEntryValid
)
