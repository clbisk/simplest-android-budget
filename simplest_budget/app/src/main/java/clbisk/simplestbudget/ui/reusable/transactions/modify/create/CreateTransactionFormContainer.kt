package clbisk.simplestbudget.ui.reusable.transactions.modify.create

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.ui.reusable.transactions.modify.ModifyTransactionForm
import clbisk.simplestbudget.ui.reusable.transactions.modify.edit.NewTransactionViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateTransactionFormContainer(
	navBack: () -> Unit,
	paddingValues: PaddingValues,
	viewModel: NewTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val input = viewModel.inputState.collectAsState().value.input!!

	ModifyTransactionForm(
		input,
		onInputChange = viewModel::onUpdate,
		onSave = {
			coroutineScope.launch {
				viewModel.saveNewCategory()
				navBack()
			}
		},
		paddingValues = paddingValues
	)
}
