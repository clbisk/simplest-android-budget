package clbisk.simplestbudget.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.glance.layout.Box
import androidx.glance.text.Text
import clbisk.simplestbudget.widget.data.WidgetModel
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import clbisk.simplestbudget.widget.data.WidgetState.Empty
import clbisk.simplestbudget.widget.data.WidgetState.Loading
import clbisk.simplestbudget.widget.ui.BudgetAtAGlance
import clbisk.simplestbudget.widget.ui.ZeroState

@Composable
fun SimplestBudgetWidgetContent(
	repo: WidgetModelRepository,
	widgetId: Int,
) {
	when (val model = repo.loadModel(widgetId).collectAsState(Loading).value) {
		is WidgetModel -> {
			BudgetAtAGlance(model)
		}
		is Loading -> {
			Box { Text("Loading...") }
		}
		is Empty -> {
			ZeroState()
		}
	}
}
