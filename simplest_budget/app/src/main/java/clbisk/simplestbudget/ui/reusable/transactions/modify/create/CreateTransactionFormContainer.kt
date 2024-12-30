package clbisk.simplestbudget.ui.reusable.transactions.modify.create

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.transactions.modify.ModifyTransactionForm

@Composable
fun CreateTransactionFormContainer(
	navBack: () -> Unit,
	paddingValues: PaddingValues,
) {
	ModifyTransactionForm(
		paddingValues = paddingValues,
		navAway = navBack,
	)
}
