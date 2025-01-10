package clbisk.simplestbudget.widget.newtransaction

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun WidgetAddTransactionActivityScreen(
	navAway: () -> Unit,
) {
	Scaffold { padding ->
		WidgetAddTransactionForm(
			navAway = navAway,
			paddingValues = padding
		)
	}
}