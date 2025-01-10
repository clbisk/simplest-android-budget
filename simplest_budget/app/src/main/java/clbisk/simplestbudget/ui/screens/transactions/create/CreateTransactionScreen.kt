package clbisk.simplestbudget.ui.screens.transactions.create

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.ui.reusable.transactions.modify.create.CreateTransactionFormContainer

@Composable
fun CreateTransactionScreen(
	navBack: () -> Unit,
) {
	Scaffold(
		modifier = Modifier.fillMaxSize().padding(0.dp),
	) { paddingValues ->
		CreateTransactionFormContainer(
			navBack,
			paddingValues,
		)
	}
}
