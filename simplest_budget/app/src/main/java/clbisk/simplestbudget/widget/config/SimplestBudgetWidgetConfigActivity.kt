package clbisk.simplestbudget.widget.config

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.glance.appwidget.updateAll
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.theme.SimplestBudgetTheme
import clbisk.simplestbudget.widget.SimplestBudgetWidget
import clbisk.simplestbudget.widget.data.WidgetModel
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SimplestBudgetConfigActivity: ComponentActivity() {
	@Inject
	lateinit var widgetModelRepository: WidgetModelRepository

	@Inject
	lateinit var transactionRepository: TransactionRecordsRepository

	private fun onCategoryClick(appWidgetId: Int, category: BudgetCategory, spendingLimit: Float) {
		runBlocking {
			val transactionTotal = transactionRepository
				.getTransactionTotalForCategory(category.id).first()

			widgetModelRepository.createOrUpdate(
				WidgetModel(
					appWidgetId,
					category.id,
					category.categoryName,
					spendingLimit,
					remainingThisMonth = spendingLimit - transactionTotal,
				),
			)

			SimplestBudgetWidget().updateAll(this@SimplestBudgetConfigActivity)

			val resultValue = Intent().putExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				appWidgetId,
			)

			setResult(RESULT_OK, resultValue)
			finish()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val modifier = Modifier.fillMaxSize()

		val appWidgetId = intent?.extras?.getInt(
			AppWidgetManager.EXTRA_APPWIDGET_ID,
			AppWidgetManager.INVALID_APPWIDGET_ID,
		) ?: AppWidgetManager.INVALID_APPWIDGET_ID

		val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
		setResult(RESULT_CANCELED, resultValue)

		enableEdgeToEdge()
		setContent {
			SimplestBudgetTheme {
				Scaffold(
					modifier = modifier,
					topBar = { WidgetConfigAppBar() },
				) {
					WidgetConfigContent(
						appWidgetId,
						onCategoryClick = this::onCategoryClick,
						contentPadding = it,
					)
				}
			}
		}
	}
}
