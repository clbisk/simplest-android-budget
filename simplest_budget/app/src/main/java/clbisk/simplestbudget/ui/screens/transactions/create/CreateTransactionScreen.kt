package clbisk.simplestbudget.ui.screens.transactions.create

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.transactions.modify.create.CreateTransactionFormContainer

@Composable
fun CreateTransactionScreen(
	navBack: () -> Unit,
) {
	Scaffold { paddingValues ->
		CreateTransactionFormContainer(
			navBack,
			paddingValues,
		)
	}
}