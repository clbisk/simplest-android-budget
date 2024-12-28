package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle

@Composable
fun TransactionsForCategoryScreen(
	navToEditCategory: (String) -> Unit,
	navToEditTransactionRecord: (Int) -> Unit,
	navToCreateTransaction: (String) -> Unit,
	savedStateHandle: SavedStateHandle = SavedStateHandle(),
) {
	Scaffold(
		floatingActionButton = {
			TransactionCreateFab(
				{ navToCreateTransaction(savedStateHandle["categoryName"]!!) },
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
