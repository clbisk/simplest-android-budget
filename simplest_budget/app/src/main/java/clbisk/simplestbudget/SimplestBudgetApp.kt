package clbisk.simplestbudget

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import clbisk.simplestbudget.data.SimplestBudgetAppContainer
import clbisk.simplestbudget.data.SimplestBudgetDataContainer
import clbisk.simplestbudget.ui.navigation.AppNavGraph

class SimplestBudgetApp : Application() {
	lateinit var container: SimplestBudgetAppContainer

	override fun onCreate() {
		super.onCreate()
		container = SimplestBudgetDataContainer(this)
	}
}

@Composable
fun SimplestBudgetUI(navController: NavHostController = rememberNavController()) {
	AppNavGraph(navController = navController)
}
