package clbisk.simplestbudget.ui.appscreens

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.budgetcategories.create.CreateCategoryFormContainer

@Composable
fun CreateBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	CreateCategoryFormContainer()
}
