package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R

@Composable
fun TransactionCreateFab(
	navToCreateTransaction: () -> Unit,
) {
	FloatingActionButton(
		shape = MaterialTheme.shapes.medium,
		onClick = { navToCreateTransaction() },
	) {
		Icon(
			imageVector = Icons.Default.Add,
			contentDescription = stringResource(R.string.add_transaction_title),
		)
	}
}
