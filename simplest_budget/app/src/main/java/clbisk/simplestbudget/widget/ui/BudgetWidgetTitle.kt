package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.glance.ImageProvider
import androidx.glance.appwidget.components.TitleBar
import clbisk.simplestbudget.R

@Composable
fun BudgetWidgetTitle(
	categoryName: String?,
	widgetId: Int,
) {
	TitleBar(
		title = categoryName ?: stringResource(R.string.allCategoriesTitle),
		actions = { ConfigureActionButton(widgetId) },
		startIcon = ImageProvider(R.drawable.baseline_attach_money_24)
	)
}
