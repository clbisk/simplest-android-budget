package clbisk.simplestbudget.ui.reusable.budgetcategories.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import androidx.compose.foundation.lazy.items as lazyItems

@Composable
fun BudgetCategoryList(
	budgetCategoryList: List<BudgetCategory>,
	onItemClick: (BudgetCategory) -> Unit,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding,
		verticalArrangement = Arrangement.spacedBy(2.dp),
		) {
		lazyItems(items = budgetCategoryList, key = { it.id }) {
			BudgetCategoryItem(it,
				itemModifier = Modifier.clickable { onItemClick(it) }
			)
		}
	}
}
