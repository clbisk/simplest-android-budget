package clbisk.simplest_budget.ui.budgetCategories.createCategory

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplest_budget.ui.AppViewModelProvider
import clbisk.simplest_budget.ui.budgetCategories.CategoryInputViewModel

@Composable
fun CreateBudgetCategoryScreen(
	viewModel: CategoryInputViewModel = viewModel(factory = AppViewModelProvider.Factory),
	navBack: () -> Unit,
) {
}
