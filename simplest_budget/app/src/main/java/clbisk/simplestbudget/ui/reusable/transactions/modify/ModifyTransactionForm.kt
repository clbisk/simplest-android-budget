package clbisk.simplestbudget.ui.reusable.transactions.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.util.getDeviceCurrencySymbol
import kotlinx.coroutines.launch

@Composable
fun ModifyTransactionForm (
	paddingValues: PaddingValues,
	navAway: () -> Unit,
	viewModel: ModifyTransactionViewModel = hiltViewModel(),
) {
	val coroutineScope = rememberCoroutineScope()

	val inputState = viewModel.inputState.collectAsState()
	val input = inputState.value.input
	val shouldEnableEdits = !inputState.value.loading

	fun save() {
		coroutineScope.launch {
			viewModel.saveTransaction()
			navAway()
		}
	}

	Column(
		modifier = Modifier.padding(paddingValues).fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Row(
			modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
		) {
			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
			) {
				ModifyTransactionFormInputs(
					enabled = shouldEnableEdits,
					categoryName = input.inCategoryName ?: "",
					currencySymbol = getDeviceCurrencySymbol(),
					transactionAmtInput = input.currencyAmount,
					description = input.description,
					updateCategory = viewModel::updateCategory,
					updateTransactionAmtInput = viewModel::updateAmount,
					updateDescription = viewModel::updateDescription,
				)
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
