package clbisk.simplestbudget.ui.appscreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.budgetcategories.list.BudgetCategoryListContainer

@Composable
fun HomeScreen(
	navToCreateCategory: () -> Unit
) {
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(
				shape = MaterialTheme.shapes.medium,
				onClick = navToCreateCategory
			) {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = stringResource(R.string.create_category_title)
				)
			}
		}
	) { innerPadding ->
		BudgetCategoryListContainer(
			contentPadding = innerPadding,
		)
	}
}
