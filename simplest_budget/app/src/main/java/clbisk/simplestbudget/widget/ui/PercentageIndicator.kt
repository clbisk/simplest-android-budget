package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun PercentageIndicator(
	remainingRatio: Float,
) {
	Row(
		modifier = GlanceModifier.fillMaxWidth().wrapContentHeight(),
	) {
		Box {
			LinearProgressIndicator(
				progress = remainingRatio,
				color = GlanceTheme.colors.primary,
				backgroundColor = GlanceTheme.colors.tertiary,
				modifier = GlanceModifier.fillMaxWidth().wrapContentHeight(),
			)

			Column(
				modifier = GlanceModifier.fillMaxWidth().padding(start = 5.dp),
				horizontalAlignment = Alignment.Start,
				verticalAlignment = Alignment.Bottom,
			) {
				Text("${(remainingRatio * 100).toInt()}%",
					style = TextStyle(
						color = GlanceTheme.colors.widgetBackground,
						fontSize = 10.sp,
						fontWeight = FontWeight.Bold,
					),
				)
			}
		}
	}
}
