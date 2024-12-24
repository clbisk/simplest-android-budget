package clbisk.simplestbudget.ui.budgetcategories.modify.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateCategoryFormContainer(
	navUp: () -> Unit,
	viewModel: NewCategoryViewModel = hiltViewModel(),
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
