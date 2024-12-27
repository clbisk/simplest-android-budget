package clbisk.simplestbudget.ui.transactions.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TransactionsForCategoryScreen(
	navToEditCategory: (String) -> Unit,
	navToEditTransactionRecord: (Int) -> Unit,
	contentPadding: PaddingValues = PaddingValues(0.dp),
) {
	TransactionsForCategoryListContainer(
		navToEditCategory,
		navToEditTransactionRecord,
		contentPadding,
	)
}
