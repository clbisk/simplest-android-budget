package clbisk.simplestbudget.ui.transactions.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R

@Composable
fun TransactionsForCategoryListContainer (
	navToEditCategory: (String) -> Unit,
	navToEditTransaction: (Int) -> Unit,
	contentPadding: PaddingValues = PaddingValues(0.dp),
	viewModel: TransactionsForCategoryViewModel = hiltViewModel()
) {
	val transactionListState by viewModel.transactionsListState.collectAsState()
	val transactionList = transactionListState.transactionList

	val categoryState = viewModel.categoryState.collectAsState().value

	Row {
		LazyLoadCategoryCard(
			categoryState,
			modifier = Modifier.clickable { navToEditCategory(categoryState.categoryName) }
		)
	}

	Row {
		if (transactionList == null) {
			Text(
				text = stringResource(R.string.loading_categories_message),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.padding(contentPadding)
			)
		} else if (transactionList.isEmpty()) {
			Text(
				text = stringResource(R.string.no_transactions_message),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.padding(contentPadding)
			)
		} else {
			TransactionsForCategoryList(
				transactionList,
				onTransactionClick = { navToEditTransaction(it.id)},
				contentPadding
			)
		}
	}
}