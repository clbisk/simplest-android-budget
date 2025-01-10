package clbisk.simplestbudget.ui.screens.transactions.list.forcategory

import androidx.compose.runtime.Composable

@Composable
fun TransactionsForCategoryScreen(
	navToEditCategory: (Int) -> Unit,
	navToEditTransactionRecord: (Int) -> Unit,
	navToCreateTransaction: (Int) -> Unit,
) {
	TransactionsForCategoryListContainer(
		navToEditCategory,
		navToEditTransactionRecord,
		navToCreateTransaction,
	)
}
