package clbisk.simplest_budget_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Widget to display $ amount remaining in current budget.
 */
class RemainingTotalReceiver : GlanceAppWidgetReceiver() {
	override val glanceAppWidget: GlanceAppWidget = RemainingTotalWidget()
}

class RemainingTotalWidget : GlanceAppWidget() {
	override suspend fun provideGlance(context: Context, id: GlanceId) {
		withContext(Dispatchers.Default) {
			provideContent {
				Text(R.string.hello_world)
			}
		}
	}
}
