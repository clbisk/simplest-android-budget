package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.transactions.modify.ModifyTransactionForm

@Composable
fun EditTransactionFormContainer(
	navUp: () -> Unit,
	paddingValues: PaddingValues,
) {
	ModifyTransactionForm (
		navAway = navUp,
		paddingValues = paddingValues,
	)
}
