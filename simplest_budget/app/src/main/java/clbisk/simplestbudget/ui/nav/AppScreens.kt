package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import clbisk.simplestbudget.ui.HomeScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.create.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.edit.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.transactions.TransactionsForCategoryList

fun navToCreateCategory(navController: NavController): () -> Unit = {
	navController.navigate(Route.CreateCategory)
}

fun navToEditCategory(navController: NavController): (categoryName: String) -> Unit = {
		categoryName: String -> navController.navigate("${Route.EditCategory}/${categoryName}")
}

val AppScreens: Map<Route, @Composable (navController: NavController) -> Unit> = mapOf(
	Route.Home to {
		navController -> HomeScreen(
			navToCreateCategory = navToCreateCategory(navController),
			navToEditCategory = navToEditCategory(navController),
		)
	},
	Route.CreateCategory to {
		navController -> CreateBudgetCategoryScreen(
			navUp = navController::navigateUp
		)
	},
	Route.EditCategory to {
		navController -> EditBudgetCategoryScreen(
			navUp = navController::navigateUp
		)
	},
	Route.TransactionsForCat to { TransactionsForCategoryList() }
)
