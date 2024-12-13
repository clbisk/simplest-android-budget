package clbisk.simplestbudget.ui.budgetcategories.create

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplestbudget.ui.AppViewModelProvider
import clbisk.simplestbudget.ui.budgetcategories.edit.EditCategoryViewModel

@Composable
fun CreateCategoryFormContainer(
	viewModel: EditCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
	CreateBudgetCategoryForm(
		inputState = viewModel.categoryInputState,
		onChange = viewModel::updateInput,
		onSave = { }
	)
}
