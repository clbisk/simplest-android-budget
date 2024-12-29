package clbisk.simplestbudget.ui.reusable.transactions.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect.SimpleCategorySelect

@Composable
fun ModifyTransactionForm (
	input: TransactionInput?,
	onInputChange: (TransactionInput) -> Unit,
	onSave: () -> Unit,
	paddingValues: PaddingValues,
) {
	Column(
		modifier = Modifier.padding(paddingValues).height(IntrinsicSize.Max),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Column(
			modifier = Modifier.padding(paddingValues).height(IntrinsicSize.Max),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			SimpleCategorySelect (
				onValueSelect = { newCat -> onInputChange(input!!.copy(inCategoryName = newCat)) },
				initialValue = input?.inCategoryName,
			)

			Row {
				TextField(
					value = input?.currencyAmount ?: "",
					onValueChange = { newAmt -> onInputChange(input!!.copy(currencyAmount = newAmt)) },
					enabled = input != null,
				)
			}
			Row {
				TextField(
					value = input?.description ?: "",
					onValueChange = { newTxt -> onInputChange(input!!.copy(description = newTxt)) },
				)
			}
		}
		FilledTonalButton(
			onClick = onSave,
			enabled = input != null,
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
