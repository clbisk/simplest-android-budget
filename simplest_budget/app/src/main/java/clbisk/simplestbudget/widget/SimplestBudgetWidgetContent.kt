package clbisk.simplestbudget.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import clbisk.simplestbudget.widget.data.WidgetModel
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import clbisk.simplestbudget.widget.data.WidgetState.Loading
import clbisk.simplestbudget.widget.ui.BudgetAtAGlance
import clbisk.simplestbudget.widget.ui.ZeroState

@Composable
fun SimplestBudgetWidgetContent(
	repo: WidgetModelRepository,
	widgetId: Int,
) {
	val model = repo.loadModel(widgetId).collectAsState(Loading).value

	when (model) {
		is WidgetModel -> {
			BudgetAtAGlance(categoryName = model.forCategoryName)
		}
		else -> ZeroState()
	}
}
