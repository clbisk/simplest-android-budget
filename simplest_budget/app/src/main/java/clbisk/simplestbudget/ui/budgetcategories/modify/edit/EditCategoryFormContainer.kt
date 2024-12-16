package clbisk.simplestbudget.ui.budgetcategories.modify.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplestbudget.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun EditCategoryFormContainer(
	navUp: () -> Unit,
	viewModel: EditCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
	val coroutineScope = rememberCoroutineScope()

	val editState by viewModel.inputState.collectAsState()

	EditBudgetCategoryForm (
		name = editState.input?.categoryName ?: "",
		limit = editState.input?.spendingLimit ?: 0,
		editState.stateLoaded,
		onNameInputChange = viewModel::onNameInputChange,
		onLimitInputChange = viewModel::onLimitInputChange,
		onSave = {
			coroutineScope.launch {
				viewModel.saveEditedCategory()
				navUp()
			}
		}
	)
}
