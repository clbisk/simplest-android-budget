package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun EditTransactionFormContainer(
	navUp: () -> Unit,
	paddingValues: PaddingValues,
	viewModel: EditTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val editState by viewModel.inputState.collectAsState()

	EditTransactionForm (
		input = editState.input,
		onInputChange = viewModel::onUpdate,
		onSave = {
			coroutineScope.launch {
				viewModel.saveEditedCategory()
				navUp()
			}
		},
		paddingValues = paddingValues,
	)
}
