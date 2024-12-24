package clbisk.simplestbudget.ui.budgetcategories.modify.create

import androidx.compose.runtime.Composable

@Composable
fun CreateBudgetCategoryScreen(
	navUp: () -> Unit,
) {
	CreateCategoryFormContainer(
		navUp = navUp
	)
}
