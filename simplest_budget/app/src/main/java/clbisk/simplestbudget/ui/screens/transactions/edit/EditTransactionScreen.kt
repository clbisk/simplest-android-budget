package clbisk.simplestbudget.ui.screens.transactions.edit

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.transactions.modify.edit.EditTransactionFormContainer

@Composable
fun EditTransactionScreen(
	navUp: () -> Unit,
) {
	Scaffold { paddingValues ->
		EditTransactionFormContainer(
			navUp,
			paddingValues,
		)
	}
}
