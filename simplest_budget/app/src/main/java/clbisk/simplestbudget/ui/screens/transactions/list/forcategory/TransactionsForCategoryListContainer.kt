package clbisk.simplestbudget.ui.screens.transactions.list.forcategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.ui.reusable.transactions.list.TransactionListContainer

@Composable
fun TransactionsForCategoryListContainer (
	navToEditCategory: (Int) -> Unit,
	navToEditTransaction: (Int) -> Unit,
	navToCreateTransaction: (Int) -> Unit,
	viewModel: TransactionsForCategoryViewModel = hiltViewModel(),
) {
	val transactionListState by viewModel.transactionsListState.collectAsState()
	val transactionList = transactionListState.transactionList

	val categoryState = viewModel.categoryData.collectAsState()
	val transactionTotal = viewModel.transactionsTotalState.collectAsState()

	Scaffold(
		floatingActionButton = {
			TransactionCreateFab { navToCreateTransaction(viewModel.catId) }
		},

	) { paddingValues ->
		Column(
			modifier = Modifier.padding(paddingValues),
		) {
			Row(
				modifier = Modifier.padding(bottom = 0.dp)
			) {
				LazyLoadCategoryCard(
					categoryName = categoryState.value.categoryName,
					modifier = Modifier
						.clickable { navToEditCategory(viewModel.catId) },
					spendingLimit = categoryState.value.spendingLimit,
					transactionTotal = transactionTotal.value,
				)
			}

			Row(
				modifier = Modifier.padding(bottom = 0.dp),
				horizontalArrangement = Arrangement.Center,
			) {
				TransactionListContainer(
					transactionList,
					navToEditTransaction,
					contentPadding = paddingValues,
				)
			}
		}
	}
}
