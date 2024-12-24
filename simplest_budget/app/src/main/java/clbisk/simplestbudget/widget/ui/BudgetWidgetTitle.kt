package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.components.TitleBar
import clbisk.simplestbudget.R

@Composable
fun BudgetWidgetTitle(
	categoryName: String? = null,
) {
	TitleBar(
		title = categoryName ?: LocalContext.current.getString(R.string.allCategoriesTitle),
		startIcon = ImageProvider(R.drawable.baseline_attach_money_24)
	)
}
