package clbisk.simplest_budget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import clbisk.simplest_budget.ui.screens.createCategory.CreateBudgetCategoryForm
import clbisk.simplest_budget.ui.screens.home.Home

@Composable
fun SimplestBudgetNavGraph(
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = HomeScreen.route,
		modifier = modifier
	) {
		composable(route = HomeScreen.route) {
			Home(
				navToCreateCategory = { navController.navigate(CreateBudgetCategoryScreen.route) },
			)
		}
		composable(route = CreateBudgetCategoryScreen.route) {
			CreateBudgetCategoryForm(
				navBack = { navController.popBackStack() }
			)
		}
	}
}
