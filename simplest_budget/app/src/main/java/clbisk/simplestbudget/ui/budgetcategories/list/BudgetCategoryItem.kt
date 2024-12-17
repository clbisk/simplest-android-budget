package clbisk.simplestbudget.ui.budgetcategories.list

import androidx.compose.runtime.Composable
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.formatCurrency

@Composable
fun BudgetCategoryItem(
	category: BudgetCategory,
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
					text = category.categoryName,
					style = MaterialTheme.typography.titleLarge
				)
			}
			Row(
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = formatCurrency(category.spendingLimit),
					style = MaterialTheme.typography.titleMedium
				)
//				Spacer(Modifier.weight(1f))
			}
		}
	}
}