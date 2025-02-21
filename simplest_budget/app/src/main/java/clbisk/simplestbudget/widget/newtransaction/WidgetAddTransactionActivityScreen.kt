package clbisk.simplestbudget.widget.newtransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.ui.reusable.transactions.modify.ModifyTransactionForm

@Composable
fun WidgetAddTransactionActivityScreen(
	navAway: () -> Unit,
) {
	Scaffold(
		modifier = Modifier.padding(horizontal = 4.dp)
	) { padding ->
		Column(
			modifier = Modifier.padding(padding).fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			ModifyTransactionForm(
				navAway = navAway,
				paddingValues = padding,
			)
		}
	}
}
