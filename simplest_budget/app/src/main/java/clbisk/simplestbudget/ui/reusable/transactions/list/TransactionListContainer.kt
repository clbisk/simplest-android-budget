package clbisk.simplestbudget.ui.reusable.transactions.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.R
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord

@Composable
fun TransactionListContainer(
	transactionList: List<TransactionRecord>?,
	navToEditTransaction: (Int) -> Unit,
	contentPadding: PaddingValues = PaddingValues(5.dp),
) {
	Row {
		if (transactionList == null) {
			Text(
				text = stringResource(R.string.loading_categories_message),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.padding(contentPadding),
			)
		} else if (transactionList.isEmpty()) {
			Text(
				text = stringResource(R.string.no_transactions_message),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.padding(contentPadding),
			)
		} else {
			TransactionList(
				transactionList,
				onTransactionClick = { navToEditTransaction(it.id)},
				contentPadding,
			)
		}
	}
}
