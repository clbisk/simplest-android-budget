package clbisk.simplestbudget.widget.config

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.reusable.budgetcategories.list.BudgetCategoryItem

@Composable
fun WidgetConfigContent(
	appWidgetId: Int,
	onCategoryClick: (Int, BudgetCategory, Float) -> Unit,
	contentPadding: PaddingValues,
	viewModel: WidgetConfigViewModel = hiltViewModel(),
) {
	val categoryUiState by viewModel.categoryListState.collectAsState()
	val categoryList = categoryUiState.categoryList ?: listOf()

	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		contentPadding = contentPadding,
	) {
		items(categoryList) {
			BudgetCategoryItem(it,
				itemModifier = Modifier.clickable {
					onCategoryClick(
						appWidgetId,
						it,
						it.spendingLimit,
					)
				}
			)
		}
	}
}
