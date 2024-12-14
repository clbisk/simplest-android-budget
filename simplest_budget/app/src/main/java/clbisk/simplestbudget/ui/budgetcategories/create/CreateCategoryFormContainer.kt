package clbisk.simplestbudget.ui.budgetcategories.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplestbudget.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun CreateCategoryFormContainer(
	navUp: () -> Unit,
	viewModel: NewCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
	val coroutineScope = rememberCoroutineScope()

	val nameInputState by viewModel.categoryName.observeAsState("")
	val spendingLimit by viewModel.spendingLimit.observeAsState(0)
	val isValid by viewModel.isValid.observeAsState(false)

	CreateBudgetCategoryForm(
		name = nameInputState,
		limit = spendingLimit,
		isValid,
		onNameChange = viewModel::onNameUpdate,
		onLimitChange = viewModel::onLimitUpdate,
		onSave = {
			coroutineScope.launch {
				viewModel.saveNewCategory()
				navUp()
			}
		}
	)
}
