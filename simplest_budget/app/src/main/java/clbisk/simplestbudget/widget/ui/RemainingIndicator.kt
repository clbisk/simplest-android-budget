package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun RemainingIndicator(
	moneyIcon: String,
	remainingAmount: Float,
	limit: Float,
) {
	Row(
		modifier = GlanceModifier.fillMaxWidth().wrapContentHeight().padding(top = 0.dp),
	) {
		Text("remaining: ", style = TextStyle(fontSize = 15.sp))
		Text(
			"$moneyIcon $remainingAmount /",
			style = TextStyle(
				color = GlanceTheme.colors.onSurface,
				fontSize = 20.sp,
			),
			modifier = GlanceModifier.padding(end = 5.dp),
		)
		Text("$moneyIcon$limit", style = TextStyle(fontSize = 12.sp))
	}
}
