package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import clbisk.simplestbudget.ui.screens.budgetcategories.create.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.screens.budgetcategories.edit.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.screens.home.HomeScreen
import clbisk.simplestbudget.ui.screens.transactions.create.CreateTransactionScreen
import clbisk.simplestbudget.ui.screens.transactions.edit.EditTransactionScreen
import clbisk.simplestbudget.ui.screens.transactions.list.forcategory.TransactionsForCategoryScreen

fun navTo(toDest: TopLevelDestination, navController: NavController): (args: List<String>?) -> Unit = {
	args ->
		val routeName =
			if (NavArgs[toDest.route]?.isEmpty() == false)
				"${toDest.name}/${args!!.joinToString(separator = ",")}"
			else toDest.name

		navController.navigate(routeName)
}

val AppScreens: Map<Route, @Composable (navController: NavController) -> Unit> = mapOf(
	Route.Home to {
		HomeScreen(
			navToCreateCategory = {
				navTo(TopLevelDestination.CreateCategory, it)(null)
		    },
			navToTransactionsList = {
				cat -> navTo(TopLevelDestination.TransactionsForCat, it)(listOf(cat))
			},
		)
	},

	Route.CreateCategory to { CreateBudgetCategoryScreen(navUp = it::navigateUp) },
	Route.EditCategory to { EditBudgetCategoryScreen(navUp = it::navigateUp) },

	Route.CreateTransaction to { CreateTransactionScreen(navBack = { it.popBackStack() }) },
	Route.EditTransaction to { EditTransactionScreen(navUp = it::navigateUp) },

	Route.TransactionsForCat to {
		 TransactionsForCategoryScreen(
			navToEditCategory = {
				cat -> navTo(TopLevelDestination.EditCategory, it)(listOf(cat))
			},
			navToEditTransactionRecord = {
				id -> navTo(TopLevelDestination.EditCategory, it)(listOf("$id"))
			},
			navToCreateTransaction = {
				cat -> navTo(TopLevelDestination.CreateTransaction, it)(listOf(cat))
			},
		)
	},
)
