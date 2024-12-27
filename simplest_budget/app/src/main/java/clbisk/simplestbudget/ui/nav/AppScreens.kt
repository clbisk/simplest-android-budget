package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import clbisk.simplestbudget.ui.HomeScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.create.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.budgetcategories.modify.edit.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.transactions.list.TransactionsForCategoryScreen
import clbisk.simplestbudget.ui.transactions.modify.create.CreateTransactionScreen
import clbisk.simplestbudget.ui.transactions.modify.edit.EditTransactionScreen

fun navToCreateCategory(navController: NavController): () -> Unit = {
	navController.navigate(TopLevelDestination.CreateCategory.name)
}

fun navToEditCategory(navController: NavController): (categoryName: String) -> Unit = {
	categoryName: String -> navController.navigate("${TopLevelDestination.EditCategory.name}/${categoryName}")
}

fun navToTransactionsForCat(navController: NavController): (categoryName: String) -> Unit = {
	categoryName: String -> navController.navigate("${TopLevelDestination.TransactionsForCat.name}/${categoryName}")
}

fun navToEditTransaction(navController: NavController): (transactionId: Int) -> Unit = {
		transactionId: Int -> navController.navigate("${TopLevelDestination.EditTransaction.name}/${transactionId}")
}

val AppScreens: Map<Route, @Composable (navController: NavController) -> Unit> = mapOf(
	Route.Home to {
		navController -> HomeScreen(
			navToCreateCategory = navToCreateCategory(navController),
			navToTransactionsList = navToTransactionsForCat(navController),
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
	Route.TransactionsForCat to {
		navController -> TransactionsForCategoryScreen(
			navToEditCategory = navToEditCategory(navController),
			navToEditTransactionRecord = navToEditTransaction(navController),
		)
								},
	Route.CreateTransaction to { CreateTransactionScreen() },
	Route.EditTransaction to { EditTransactionScreen() },
)
