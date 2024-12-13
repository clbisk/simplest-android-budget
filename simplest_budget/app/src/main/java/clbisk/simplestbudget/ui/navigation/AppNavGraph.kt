package clbisk.simplestbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import clbisk.simplestbudget.ui.appscreens.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.appscreens.HomeScreen

@Composable
fun AppNavGraph(
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = HomeDestination.route,
		modifier = modifier
	) {
		composable(route = HomeDestination.route) {
			HomeScreen(
				navToCreateCategory = {
					navController.navigate(CreateBudgetCategoryDestination.route)
									  },
			)
		}
		composable(route = CreateBudgetCategoryDestination.route) {
			CreateBudgetCategoryScreen(
				navUp = {
					navController.navigateUp()
				},
			)
		}
	}
}
