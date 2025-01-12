package clbisk.simplestbudget.ui.reusable.transactions.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import androidx.compose.foundation.lazy.items as lazyItems

@Composable
fun TransactionList(
	transactionsList: List<TransactionRecord>,
	onTransactionClick: (TransactionRecord) -> Unit,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding,
		verticalArrangement = Arrangement.spacedBy(10.dp),
		modifier = Modifier.padding(top = 0.dp)
	) {
		lazyItems(items = transactionsList, key = { it.id }) {
			TransactionRecordItem(it,
				itemModifier = Modifier.clickable { onTransactionClick(it) }
			)
		}
	}
}
