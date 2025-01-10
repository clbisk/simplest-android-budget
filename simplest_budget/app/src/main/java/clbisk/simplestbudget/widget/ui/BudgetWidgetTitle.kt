package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import clbisk.simplestbudget.R

@Composable
fun BudgetWidgetTitle(
	categoryName: String,
) {
	Column(
		modifier = GlanceModifier.wrapContentSize(),
	) {
		CircleIconButton(
			ImageProvider(R.drawable.baseline_receipt_24),
			contentDescription = "budgetTrackerIcon",
			onClick = {},
			backgroundColor = null,
			modifier = GlanceModifier.wrapContentSize()
				.padding(top = 1.dp, start = 2.dp, end = 5.dp),
		)
	}
	Column {
		Text(categoryName,
			style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
		)
	}
}