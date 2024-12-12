package clbisk.simplest_budget.ui.screens.home.budget_category_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items as lazyItems
import clbisk.simplest_budget.data.budgetCategory.BudgetCategory

@Composable
fun BudgetCategoryList(
	budgetCategoryList: List<BudgetCategory>,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding
	) {
		lazyItems(items = budgetCategoryList, key = { it.id }) {
			category -> BudgetCategoryItem(category)
		}
	}
}