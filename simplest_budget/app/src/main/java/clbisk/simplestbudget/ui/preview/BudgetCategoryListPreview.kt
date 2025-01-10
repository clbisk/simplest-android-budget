package clbisk.simplestbudget.ui.preview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.R
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.reusable.budgetcategories.list.BudgetCategoryList

val PREVIEW_CATEGORIES = listOf(
	BudgetCategory(1, "Hi", 100.toFloat()),
	BudgetCategory(2, "Bye", 1000.toFloat()),
)

val LocalPreviewMode: ProvidableCompositionLocal<List<BudgetCategory>> = compositionLocalOf { listOf() }

@Preview
@Composable
fun BudgetCategoryListPreview() {
	CompositionLocalProvider(LocalPreviewMode provides PREVIEW_CATEGORIES) {
		Scaffold(
			floatingActionButton = {
				FloatingActionButton(
					shape = MaterialTheme.shapes.medium,
					onClick = {}
				) {
					Icon(
						imageVector = Icons.Default.Add,
						contentDescription = stringResource(R.string.create_category_title)
					)
				}
			},
			modifier = Modifier.padding(PaddingValues(5.dp))
		) { padding ->
			BudgetCategoryList(
				budgetCategoryList = LocalPreviewMode.current,
				onItemClick = {},
				contentPadding = padding,
			)
		}
	}
}
