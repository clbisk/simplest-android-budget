package clbisk.simplest_budget

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import clbisk.simplest_budget.data.SimplestBudgetAppContainer
import clbisk.simplest_budget.data.SimplestBudgetDataContainer
import clbisk.simplest_budget.ui.navigation.SimplestBudgetNavGraph

class SimplestBudgetApp : Application() {
	lateinit var container: SimplestBudgetAppContainer

	override fun onCreate() {
		super.onCreate()
		container = SimplestBudgetDataContainer(this)
	}
}

@Composable
fun SimplestBudgetUI(navController: NavHostController = rememberNavController()) {
	SimplestBudgetNavGraph(navController = navController)
}
