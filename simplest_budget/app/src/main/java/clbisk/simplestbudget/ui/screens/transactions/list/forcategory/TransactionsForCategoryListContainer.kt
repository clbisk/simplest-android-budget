package clbisk.simplestbudget.ui.screens.transactions.list.forcategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
	navToEditCategory: (String) -> Unit,
	navToEditTransaction: (Int) -> Unit,
	navToCreateTransaction: (String) -> Unit,
	viewModel: TransactionsForCategoryViewModel = hiltViewModel(),
) {
	val transactionListState by viewModel.transactionsListState.collectAsState()
	val transactionList = transactionListState.transactionList

	val categoryState = viewModel.categoryData.collectAsState()
	val transactionTotal = viewModel.transactionsTotalState.collectAsState()

	Scaffold(
		floatingActionButton = {
			TransactionCreateFab { navToCreateTransaction(viewModel.nameArg) }
		},

	) { paddingValues ->
		Column(
			modifier = Modifier.padding(paddingValues),
		) {
			Row {
				LazyLoadCategoryCard(
					categoryName = viewModel.nameArg,
					modifier = Modifier
						.padding(paddingValues)
						.clickable { navToEditCategory(viewModel.nameArg) },
					spendingLimit = categoryState.value.spendingLimit,
					transactionTotal = transactionTotal.value,
				)
			}

			Spacer(Modifier.height(2.dp))

			Row(
				modifier = Modifier.padding(paddingValues),
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
