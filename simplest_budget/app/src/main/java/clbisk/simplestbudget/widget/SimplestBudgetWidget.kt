package clbisk.simplestbudget.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import clbisk.simplestbudget.widget.model.WidgetModelRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SimplestBudgetWidgetReceiver : GlanceAppWidgetReceiver() {
	override val glanceAppWidget: GlanceAppWidget = SimplestBudgetWidget()

	@Inject
	lateinit var repository: WidgetModelRepository

	override fun onDeleted(context: Context, appWidgetIds: IntArray) {
		super.onDeleted(context, appWidgetIds)
		repository.cleanupWidgetModels(context)
	}
}

class SimplestBudgetWidget : GlanceAppWidget() {
	override suspend fun provideGlance(context: Context, id: GlanceId) {
		val widgetId = GlanceAppWidgetManager(context).getAppWidgetId(id)
		val widgetRepo = WidgetModelRepository.get(context)

		provideContent {
			GlanceTheme {
				SimplestBudgetWidgetContent(
					repo = widgetRepo,
					widgetId,
				)
			}
		}
	}
}
