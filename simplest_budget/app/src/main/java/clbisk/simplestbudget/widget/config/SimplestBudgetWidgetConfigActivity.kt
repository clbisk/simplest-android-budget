package clbisk.simplestbudget.widget.config

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.glance.ImageProvider
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.updateAll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import clbisk.simplestbudget.R
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.budgetCategory.OfflineBudgetCategoriesRepository
import clbisk.simplestbudget.ui.budgetcategories.list.BudgetCategoryItem
import clbisk.simplestbudget.ui.theme.SimplestBudgetTheme
import clbisk.simplestbudget.widget.SimplestBudgetWidget
import clbisk.simplestbudget.widget.model.WidgetModel
import clbisk.simplestbudget.widget.model.WidgetModelRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SimplestBudgetConfigActivity @Inject constructor(
	widgetModelRepository: WidgetModelRepository,
	budgetCategoriesRepository: OfflineBudgetCategoriesRepository,
): ComponentActivity() {
	lateinit var widgetModelRepository: WidgetModelRepository

	private fun onCategoryClick(appWidgetId: Int, category: BudgetCategory) {
		runBlocking {
			widgetModelRepository.createOrUpdate(
				WidgetModel(
					appWidgetId,
					category.categoryName,
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
					topBar = { TitleBar(
						title = stringResource(R.string.widget_config_title),
						startIcon = ImageProvider(R.drawable.settings_gear),
					)}
				) {
					WidgetConfigContent(it, appWidgetId, this::onCategoryClick)
				}
			}
		}
	}
}

@Composable
fun WidgetConfigContent(
	innerPadding: PaddingValues,
	appWidgetId: Int,
	onCategoryClick: (Int, BudgetCategory) -> Unit,
	viewModel: WidgetConfigViewModel = hiltViewModel()
) {
	val categoryUiState by viewModel.categoryListState.collectAsState()
	val categoryList = categoryUiState.categoryList

	LazyColumn(
		contentPadding = innerPadding
	) {
		items(items = categoryList ?: listOf(), key = { it.categoryName }) {
			BudgetCategoryItem(it,
				itemModifier = Modifier.clickable {
					onCategoryClick(
						appWidgetId,
						it,
					)
				}
			)
		}
	}
}
