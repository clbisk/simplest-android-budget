package clbisk.simplestbudget.ui.reusable.budgetcategories.list

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
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.reusable.util.formatCurrency
import clbisk.simplestbudget.ui.reusable.util.maybeFormatCurrency

@Composable
fun BudgetCategoryItem(
	category: BudgetCategory,
	itemModifier: Modifier,
	transactionTotal: Float? = null,
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
					text = category.categoryName,
					style = MaterialTheme.typography.titleLarge
				)
			}
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = "Budget: ${formatCurrency(category.spendingLimit)}",
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