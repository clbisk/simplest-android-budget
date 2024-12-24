package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun BudgetAtAGlance(
	categoryName: String? = null,
) {
	Scaffold(
		backgroundColor = GlanceTheme.colors.widgetBackground,
		modifier = GlanceModifier.fillMaxSize(),
		titleBar = { BudgetWidgetTitle(categoryName) },
	) {
		Column(
			modifier = GlanceModifier.fillMaxSize(),
			verticalAlignment = Alignment.Top,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Row {
				Text("remaining: ?",
					modifier = GlanceModifier.padding(12.dp),
					style = TextStyle(color = GlanceTheme.colors.onSurface),
				)
			}
			Row {
				Text("? % spent",
					modifier = GlanceModifier.padding(12.dp),
					style = TextStyle(color = GlanceTheme.colors.onSurface),
				)
			}
		}
	}
}
