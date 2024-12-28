package clbisk.simplestbudget.ui.screens.budgetcategories.create

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.budgetcategories.modify.create.CreateCategoryFormContainer

@Composable
fun CreateBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	CreateCategoryFormContainer(
		navUp = navUp
	)
}
