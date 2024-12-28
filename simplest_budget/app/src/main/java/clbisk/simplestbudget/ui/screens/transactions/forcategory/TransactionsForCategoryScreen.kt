package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun TransactionsForCategoryScreen(
	navToEditCategory: (String) -> Unit,
	navToEditTransactionRecord: (Int) -> Unit,
	navToCreateTransaction: () -> Unit,
) {
	Scaffold(
		floatingActionButton = {
			TransactionCreateFab(
				navToCreateTransaction,
			)
		}
	) {
		padding ->
		TransactionsForCategoryListContainer(
			navToEditCategory,
			navToEditTransactionRecord,
			paddingValues = padding,
		)
	}
}
