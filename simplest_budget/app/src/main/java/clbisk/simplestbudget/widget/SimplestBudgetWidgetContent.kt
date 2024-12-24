package clbisk.simplestbudget.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.glance.text.Text
import clbisk.simplestbudget.widget.model.WidgetModel
import clbisk.simplestbudget.widget.model.WidgetModelRepository
import clbisk.simplestbudget.widget.model.WidgetState.Loading
import clbisk.simplestbudget.widget.ui.ZeroState

@Composable
fun SimplestBudgetWidgetContent(
	repo: WidgetModelRepository,
	widgetId: Int,
) {
	val model = repo.loadModel(widgetId).collectAsState(Loading).value

	when (model) {
		is WidgetModel -> {
			Text("Hello widget model!")
		}
		else -> ZeroState()
	}
}
