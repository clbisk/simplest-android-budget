package clbisk.simplestbudget.ui.screens.transactions.forcategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.ui.reusable.transactions.list.TransactionListContainer

@Composable
fun TransactionsForCategoryListContainer (
	navToEditCategory: (String) -> Unit,
	navToEditTransaction: (Int) -> Unit,
	viewModel: TransactionsForCategoryViewModel = hiltViewModel(),
	paddingValues: PaddingValues,
) {
	val transactionListState by viewModel.transactionsListState.collectAsState()
	val transactionList = transactionListState.transactionList

	val categoryState = viewModel.categoryState.collectAsState().value

	Row {
		LazyLoadCategoryCard(
			categoryState,
			modifier = Modifier
				.padding(paddingValues)
				.clickable { navToEditCategory(categoryState.categoryName) }
		)
	}

	TransactionListContainer(
		transactionList,
		navToEditTransaction,
		contentPadding = paddingValues,
	)
}
