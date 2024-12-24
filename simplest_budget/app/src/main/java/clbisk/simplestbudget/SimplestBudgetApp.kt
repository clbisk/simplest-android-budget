package clbisk.simplestbudget

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import clbisk.simplestbudget.ui.navigation.AppNavGraph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimplestBudgetApp : Application() {}

@Composable
fun SimplestBudgetUI(navController: NavHostController = rememberNavController()) {
	AppNavGraph(navController = navController)
}
