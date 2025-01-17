package clbisk.simplestbudget.widget.ui

import android.appwidget.AppWidgetManager
import androidx.compose.runtime.Composable
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.components.CircleIconButton
import clbisk.simplestbudget.R
import clbisk.simplestbudget.widget.config.SimplestBudgetConfigActivity

@Composable
fun ConfigureActionButton(widgetId: Int) {
	val widgetIdKey = ActionParameters.Key<Int>(AppWidgetManager.EXTRA_APPWIDGET_ID)
	CircleIconButton(
		backgroundColor = null,
		imageProvider = ImageProvider(R.drawable.settings_gear),
		contentDescription = LocalContext.current.getString(R.string.settingsIconDescription),
		onClick = actionStartActivity<SimplestBudgetConfigActivity>(
			parameters = actionParametersOf(widgetIdKey to widgetId)
		),
	)
}
