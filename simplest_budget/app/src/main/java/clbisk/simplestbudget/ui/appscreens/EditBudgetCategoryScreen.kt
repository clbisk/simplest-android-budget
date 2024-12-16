package clbisk.simplestbudget.ui.appscreens

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.budgetcategories.modify.create.CreateCategoryFormContainer
import clbisk.simplestbudget.ui.budgetcategories.modify.edit.EditCategoryFormContainer

@Composable
fun EditBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	EditCategoryFormContainer(
		navUp = navUp
	)
}
