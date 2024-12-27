package clbisk.simplestbudget.ui.transactions.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import androidx.compose.foundation.lazy.items as lazyItems

@Composable
fun TransactionsForCategoryList(
	transactionsList: List<TransactionRecord>,
	onTransactionClick: (TransactionRecord) -> Unit,
	contentPadding: PaddingValues,
) {
	LazyColumn(
		contentPadding = contentPadding
	) {
		lazyItems(items = transactionsList, key = { it.id }) {
			TransactionRecordItem(it,
				itemModifier = Modifier.clickable { onTransactionClick(it) }
			)
		}
	}
}
