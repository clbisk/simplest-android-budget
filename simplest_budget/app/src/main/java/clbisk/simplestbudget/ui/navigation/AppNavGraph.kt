package clbisk.simplestbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import clbisk.simplestbudget.ui.appscreens.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.appscreens.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.appscreens.HomeScreen

enum class AppRoutes {
	HOME, CREATE_CAT, EDIT_CAT
}

@Composable
fun AppNavGraph(
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = AppRoutes.HOME.name,
		modifier = modifier
	) {
		composable(route = AppRoutes.HOME.name) {
			HomeScreen(
				navToCreateCategory = {
					navController.navigate(AppRoutes.CREATE_CAT.name)
									  },
				navToEditCategory = {
					navController.navigate("${AppRoutes.EDIT_CAT.name}/${it}")
				}
			)
		}
		composable(route = AppRoutes.CREATE_CAT.name) {
			CreateBudgetCategoryScreen(
				navUp = navController::navigateUp
			)
		}
		composable(
			route = "${AppRoutes.EDIT_CAT.name}/{categoryName}",
			arguments = listOf(navArgument("categoryName") {
				type = NavType.StringType
			})
		) {
			EditBudgetCategoryScreen(
				navUp = navController::navigateUp
			)
		}
	}
}
