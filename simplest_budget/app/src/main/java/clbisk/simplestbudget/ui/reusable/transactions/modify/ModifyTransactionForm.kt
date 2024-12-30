package clbisk.simplestbudget.ui.reusable.transactions.modify

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
import kotlinx.coroutines.launch

@Composable
fun ModifyTransactionForm (
	paddingValues: PaddingValues,
	navAway: () -> Unit,
	viewModel: ModifyTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val input = viewModel.inputState.collectAsState()

	val styleModifier = Modifier.padding(paddingValues)
		.height(IntrinsicSize.Max).width(IntrinsicSize.Max)

	Column(
		modifier = styleModifier,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Column(
			modifier = styleModifier,
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			SimpleCategorySelect (
				onValueSelect = { viewModel.onUpdate(input.value!!.copy(inCategoryName = it)) },
				initialValue = viewModel.maybeCategoryName,
			)

			Row {
				TextField(
					value = input.value?.currencyAmount ?: "",
					onValueChange = { viewModel.onUpdate(input.value!!.copy(currencyAmount = it)) },
					enabled = input.value != null,
					label = { Text("Amount") },
				)
			}
			Row {
				TextField(
					value = input.value?.description ?: "",
					onValueChange = { viewModel.onUpdate(input.value!!.copy(description = it)) },
					enabled = input.value != null,
					label = { Text("Description (optional)") },
				)
			}
		}
		FilledTonalButton(
			onClick = {
				coroutineScope.launch {
					viewModel.saveTransaction()
					navAway()
				}
			},
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
