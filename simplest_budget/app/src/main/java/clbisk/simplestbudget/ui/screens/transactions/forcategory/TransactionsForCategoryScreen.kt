package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.compose.runtime.Composable

@Composable
fun TransactionsForCategoryScreen(
	navToEditCategory: (String) -> Unit,
	navToEditTransactionRecord: (Int) -> Unit,
	navToCreateTransaction: (String) -> Unit,
) {
	TransactionsForCategoryListContainer(
		navToEditCategory,
		navToEditTransactionRecord,
		navToCreateTransaction,
	)
}
