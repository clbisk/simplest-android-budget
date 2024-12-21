package clbisk.simplestbudget.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import clbisk.simplestbudget.widget.model.WidgetModelRepository

class SimplestBudgetWidgetReceiver : GlanceAppWidgetReceiver() {
	override val glanceAppWidget: GlanceAppWidget = SimplestBudgetWidget()
}

class SimplestBudgetWidget : GlanceAppWidget() {
	override suspend fun provideGlance(context: Context, id: GlanceId) {
		val widgetId = GlanceAppWidgetManager(context).getAppWidgetId(id)
		val widgetRepo = WidgetModelRepository.get(context)

		provideContent {
			GlanceTheme {
				SimplestBudgetWidgetContent(
					repo = widgetRepo,
					widgetId
				)
			}
		}
	}
}
