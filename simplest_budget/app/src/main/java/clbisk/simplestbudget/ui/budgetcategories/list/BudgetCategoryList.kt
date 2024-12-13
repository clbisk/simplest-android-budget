package clbisk.simplestbudget.ui.budgetcategories.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items as lazyItems
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

@Composable
fun BudgetCategoryList(
	budgetCategoryList: List<BudgetCategory>,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding
	) {
		lazyItems(items = budgetCategoryList, key = { it.categoryName }) {
			category -> BudgetCategoryItem(category)
		}
	}
}