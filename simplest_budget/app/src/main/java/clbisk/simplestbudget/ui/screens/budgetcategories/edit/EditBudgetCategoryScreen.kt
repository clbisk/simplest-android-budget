package clbisk.simplestbudget.ui.screens.budgetcategories.edit

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.budgetcategories.modify.edit.EditCategoryFormContainer

@Composable
fun EditBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	EditCategoryFormContainer(
		navUp = navUp
	)
}
