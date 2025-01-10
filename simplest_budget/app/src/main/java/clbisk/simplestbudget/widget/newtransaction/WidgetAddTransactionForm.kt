package clbisk.simplestbudget.widget.newtransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect.SimpleCategorySelect
import clbisk.simplestbudget.ui.reusable.util.getDeviceCurrencySymbol
import kotlinx.coroutines.launch

@Composable
fun WidgetAddTransactionForm (
	paddingValues: PaddingValues,
	navAway: () -> Unit,
	viewModel: WidgetAddTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val inputState = viewModel.inputState.collectAsState()
	val input = inputState.value.input
	val enableEdits = !inputState.value.loading

	val styleModifier = Modifier.padding(paddingValues)
		.height(IntrinsicSize.Max).width(IntrinsicSize.Max)

	/** get local currency icon for currency input prompt */
	val symbol = getDeviceCurrencySymbol()

	fun updateAmount(newAmt: String) { viewModel.onUpdate(input.copy(currencyAmount = newAmt)) }
	fun updateDescription(newTxt: String) { viewModel.onUpdate(input.copy(description = newTxt)) }
	fun save() {
		coroutineScope.launch {
			viewModel.saveTransaction()
			navAway()
		}
	}

	Column(
		modifier = styleModifier,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Row {
			Column(
				modifier = styleModifier,
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
			) {
				SimpleCategorySelect(
					onValueSelect = viewModel::updateCategory,
					enabled = enableEdits,
					initialValue = input.inCategoryName ?: "",
				)

				Row {
					TextField(
						value = input.currencyAmount,
						onValueChange = { updateAmount(it) },
						enabled = enableEdits,
						label = { Text("Amount") },
						leadingIcon = { Text(symbol) },
					)
				}
				Row {
					TextField(
						value = input.description,
						onValueChange = { updateDescription(it) },
						enabled = enableEdits,
						label = { Text("Description (optional)") },
					)
				}
			}
		}

		Row {
			FilledTonalButton(
				onClick = { save() },
			) {
				Text(stringResource(R.string.save_button_text))
			}
		}
	}
}
