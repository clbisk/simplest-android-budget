package clbisk.simplestbudget.ui.reusable.transactions.modify.create

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateTransactionFormContainer(
	navBack: () -> Unit,
	paddingValues: PaddingValues,
	viewModel: NewTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val nameInputState by viewModel.categoryName.observeAsState("")
	val spendingLimit by viewModel.spendingLimit.observeAsState(0.toLong())
	val isValid by viewModel.isValid.observeAsState(false)

	CreateTransactionForm(
		name = nameInputState,
		limit = spendingLimit,
		isValid,
		onNameChange = viewModel::onNameUpdate,
		onLimitChange = viewModel::onLimitUpdate,
		onSave = {
			coroutineScope.launch {
				viewModel.saveNewCategory()
				navBack()
			}
		},
		padding = paddingValues,
	)
}
