package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect.CategorySelect
import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput

@Composable
fun EditTransactionForm (
	input: TransactionInput?,
	onInputChange: (TransactionInput) -> Unit,
	onSave: () -> Unit,
	paddingValues: PaddingValues,
) {
	Column(
		modifier = Modifier.padding(paddingValues)
	) {
		Column {
			CategorySelect (
				onValueSelect = { newCat -> onInputChange(input!!.copy(inCategoryName = newCat)) },
				enabled = input != null,
				initialValue = input?.inCategoryName,
			)
			OutlinedTextField(
				value = input?.currencyAmount ?: "",
				onValueChange = { newCat -> onInputChange(input!!.copy(inCategoryName = newCat)) },
				enabled = input != null,
			)
		}
		FilledTonalButton(
			onClick = onSave,
			enabled = input != null,
			shape = MaterialTheme.shapes.small
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
