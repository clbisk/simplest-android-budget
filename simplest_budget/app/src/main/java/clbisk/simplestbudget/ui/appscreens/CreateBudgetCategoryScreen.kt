package clbisk.simplestbudget.ui.appscreens

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.budgetcategories.modify.create.CreateCategoryFormContainer

@Composable
fun CreateBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	CreateCategoryFormContainer(
		navUp = navUp
	)
}
