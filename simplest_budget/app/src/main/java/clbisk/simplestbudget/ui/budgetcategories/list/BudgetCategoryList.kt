package clbisk.simplestbudget.ui.budgetcategories.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items as lazyItems
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

@Composable
fun BudgetCategoryList(
	budgetCategoryList: List<BudgetCategory>,
	onItemClick: (BudgetCategory) -> Unit,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding
	) {
		lazyItems(items = budgetCategoryList, key = { it.categoryName }) {
			BudgetCategoryItem(it,
				itemModifier = Modifier.clickable { onItemClick(it) }
			)
		}
	}
}