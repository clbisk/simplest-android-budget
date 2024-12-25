package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import clbisk.simplestbudget.ui.HomeScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.create.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.edit.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.transactions.create.CreateTransactionScreen
import clbisk.simplestbudget.ui.transactions.edit.EditTransactionScreen
import clbisk.simplestbudget.ui.transactions.list.TransactionsForCategoryList

fun navToCreateCategory(navController: NavController): () -> Unit = {
	navController.navigate(TopLevelDestination.CreateCategory.name)
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
	Route.TransactionsForCat to { TransactionsForCategoryList() },
	Route.CreateTransaction to { CreateTransactionScreen() },
	Route.EditTransaction to { EditTransactionScreen() },
)
