package clbisk.simplestbudget.ui.reusable.budgetcategories.list

data class SummedCategoryListState(
	val categoryList: List<BudgetCategoryState>? = null,
	val filteredList: List<BudgetCategoryState>? = null,
)
