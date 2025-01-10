package clbisk.simplestbudget.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.SquareIconButton
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentHeight
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.util.getDeviceCurrencySymbol
import clbisk.simplestbudget.widget.data.WidgetModel
import clbisk.simplestbudget.widget.newtransaction.AddTransactionActivity

@Composable
fun BudgetAtAGlance(
	model: WidgetModel,
) {
	val moneyIcon = getDeviceCurrencySymbol()
	val remainingAmount = model.remainingThisMonth
	val limit = model.spendingLimit

	val remainingRatio = remainingAmount / limit

	Scaffold(
		backgroundColor = GlanceTheme.colors.widgetBackground,
		modifier = GlanceModifier.fillMaxSize(),
	) {
		Box {
			Column(
				modifier = GlanceModifier.fillMaxSize()
					.padding(top = 12.dp, start = 2.dp, bottom = 0.dp),
			) {
				Row(
					horizontalAlignment = Alignment.Start,
					modifier = GlanceModifier.fillMaxWidth().wrapContentHeight()
						.padding(bottom = 5.dp),
				) {
					BudgetWidgetTitle(categoryName = model.forCategoryName)
				}

				Row(
					verticalAlignment = Alignment.Top,
					modifier = GlanceModifier.fillMaxSize().padding(bottom = 10.dp),
				) {
					Column(
						modifier = GlanceModifier.fillMaxSize(),
						verticalAlignment = Alignment.Top,
					) {
						RemainingIndicator(moneyIcon, remainingAmount, limit)
						PercentageIndicator(remainingRatio)
					}
				}
			}

			Box(
				contentAlignment = Alignment.TopEnd,
				modifier = GlanceModifier.fillMaxSize().padding(top = 10.dp)
			) {
				SquareIconButton(
					imageProvider = ImageProvider(R.drawable.baseline_add_24),
					contentDescription = "Add new Transaction",
					onClick =
						actionStartActivity<AddTransactionActivity>(
							parameters = actionParametersOf(
								ActionParameters.Key<String>("categoryId")
										to "${model.forCategoryId}"
							),
						),
					modifier = GlanceModifier.size(40.dp),
				)
			}
		}
	}
}
