package clbisk.simplestbudget.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

class NewAppWidget : GlanceAppWidgetReceiver() {
	override val glanceAppWidget: GlanceAppWidget = SimplestBudgetWidget()
}

class SimplestBudgetWidget : GlanceAppWidget() {
	override suspend fun provideGlance(context: Context, id: GlanceId) {
		provideContent {
			Text("Hello World")
		}
	}
}
