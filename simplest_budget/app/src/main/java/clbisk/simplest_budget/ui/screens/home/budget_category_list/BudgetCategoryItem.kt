package clbisk.simplest_budget.ui.screens.home.budget_category_list

import androidx.compose.runtime.Composable
import clbisk.simplest_budget.data.budgetCategory.BudgetCategory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import clbisk.simplest_budget.ui.formatCurrency

@Composable
fun BudgetCategoryItem(
	category: BudgetCategory
) {
	Card {
		Column {
			Row {
				Text(
					text = category.categoryName,
					style = MaterialTheme.typography.titleLarge
				)
			}
			Row {
				Text(
					text = formatCurrency(category.spendingLimit),
					style = MaterialTheme.typography.titleMedium
				)
				Spacer(Modifier.weight(1f))

			}
		}
	}
}