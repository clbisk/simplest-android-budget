package clbisk.simplestbudget.ui.screens.transactions.list.forcategory

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
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.util.maybeFormatCurrency

@Composable
fun LazyLoadCategoryCard(
	categoryName: String,
	spendingLimit: Float?,
	transactionTotal: Float?,
	modifier: Modifier,
) {
	Card(
		modifier = modifier
	) {
		Column(
			modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
		) {
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = categoryName,
					style = MaterialTheme.typography.titleLarge
				)
			}
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = "Budget: ${maybeFormatCurrency(spendingLimit) ?: stringResource(R.string.indicate_loading_text)}",
					style = MaterialTheme.typography.titleMedium
				)
			}
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = "Spent: ${maybeFormatCurrency(transactionTotal) ?: stringResource(R.string.indicate_loading_text)}",
					style = MaterialTheme.typography.titleMedium
				)
			}
		}
	}
}
