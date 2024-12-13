package clbisk.simplest_budget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import clbisk.simplest_budget.ui.home.HomeScreen

@Composable
fun SimplestBudgetNavGraph(
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = HomeRoute.routeId,
		modifier = modifier
	) {
		composable(route = HomeRoute.routeId) {
			HomeScreen(
//				navToCreateCategory = { navController.navigate(CreateBudgetCategoryRoute.routeId) },
			)
		}
	}
}
