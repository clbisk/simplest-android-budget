package clbisk.simplestbudget.ui.reusable.transactions.list

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import clbisk.simplestbudget.R
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.ui.reusable.util.formatCurrency
import clbisk.simplestbudget.ui.reusable.util.formatTimestamp

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
					text = formatCurrency(transaction.currencyAmount),
					style = MaterialTheme.typography.bodyLarge,
					fontWeight = FontWeight.Bold,
				)
			}

			Row(
				horizontalArrangement = Arrangement.End,
				modifier = Modifier.fillMaxWidth(),
			) {
				Text(
					text = formatTimestamp(transaction.recordedTimestamp),
					style = MaterialTheme.typography.bodyLarge,
					textAlign = TextAlign.End,
				)
			}

			TransactionDescription()
		}
	}
}
