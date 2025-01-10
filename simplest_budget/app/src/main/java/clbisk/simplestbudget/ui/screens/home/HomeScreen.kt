package clbisk.simplestbudget.ui.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.budgetcategories.list.BudgetCategoryListContainer

@Composable
fun HomeScreen(
	navToCreateCategory: () -> Unit,
	navToTransactionsList: (Int) -> Unit,
) {
	Scaffold(
		floatingActionButton = {
			BudgetCategoryCreateFab(navToCreateCategory = navToCreateCategory)
		},
	) { innerPadding ->
		BudgetCategoryListContainer(
			navToTransactionsListFor = navToTransactionsList,
			contentPadding = innerPadding,
		)
	}
}
