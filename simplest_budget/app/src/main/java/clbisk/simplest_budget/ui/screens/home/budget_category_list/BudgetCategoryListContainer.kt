package clbisk.simplest_budget.ui.screens.home.budget_category_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import clbisk.simplest_budget.R
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplest_budget.ui.SimplestBudgetViewModelProvider

@Composable
fun BudgetCategoryListContainer(
	contentPadding: PaddingValues = PaddingValues(0.dp),
	viewModel: CategoryListViewModel = viewModel(factory = SimplestBudgetViewModelProvider.Factory)
) {
	val categoryUiState by viewModel.categoryListState.collectAsState()
	val categoryList = categoryUiState.categoryList

	if (categoryList == null) {
		Text(
			text = stringResource(R.string.loading_categories_message),
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.titleLarge,
			modifier = Modifier.padding(contentPadding)
		)
	} else if (categoryList.isEmpty()) {
		Text(
			text = stringResource(R.string.no_categories_message),
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.titleLarge,
			modifier = Modifier.padding(contentPadding)
		)
	} else {
		BudgetCategoryList(
			categoryList,
			contentPadding = contentPadding
		)
	}
}