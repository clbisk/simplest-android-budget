package clbisk.simplestbudget.widget.newtransaction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.nav.args.NavArgs
import clbisk.simplestbudget.ui.theme.SimplestBudgetTheme
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTransactionActivity: ComponentActivity() {
	@Inject
	lateinit var transactionRepository: TransactionRecordsRepository
	@Inject
	lateinit var categoriesRepository: BudgetCategoriesRepository
	@Inject
	lateinit var widgetRepository: WidgetModelRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val categoryId = intent?.extras?.getString("categoryId")

		enableEdgeToEdge()
		setContent {
			SimplestBudgetTheme {
				val navController = rememberNavController()
				NavHost(
					navController,
					startDestination = "CreateTransaction/$categoryId"
				) {
					composable("CreateTransaction/{${NavArgs.CAT_ID.name}}",
						arguments = listOf(navArgument(NavArgs.CAT_ID.name) {})
					) {
						WidgetAddTransactionActivityScreen(
							navAway = { finish() },
						)
					}
				}
			}
		}
	}
}
