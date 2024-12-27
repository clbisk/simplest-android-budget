package clbisk.simplestbudget.ui.transactions.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.ui.formatCurrency

@Composable
fun TransactionRecordItem(
	transaction: TransactionRecord,
	itemModifier: Modifier,
) {
	Card(
		modifier = itemModifier
	) {
		Column(
			modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
		) {
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = transaction.inCategoryName,
					style = MaterialTheme.typography.titleLarge
				)
			}
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = formatCurrency(transaction.wholeCurrencyAmount, transaction.fractionalCurrencyAmount),
					style = MaterialTheme.typography.titleMedium
				)
			}
		}
	}
}
