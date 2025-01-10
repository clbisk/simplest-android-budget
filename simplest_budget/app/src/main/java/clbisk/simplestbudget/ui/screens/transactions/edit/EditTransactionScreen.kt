package clbisk.simplestbudget.ui.screens.transactions.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import clbisk.simplestbudget.ui.reusable.transactions.modify.edit.EditTransactionFormContainer

@Composable
fun EditTransactionScreen(
	navUp: () -> Unit,
) {
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { paddingValues ->
		EditTransactionFormContainer(
			navUp,
			paddingValues,
		)
	}
}
