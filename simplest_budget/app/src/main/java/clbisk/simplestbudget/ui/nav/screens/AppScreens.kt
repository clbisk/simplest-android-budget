package clbisk.simplestbudget.ui.nav.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import clbisk.simplestbudget.ui.nav.routes.Route
import clbisk.simplestbudget.ui.nav.routes.TopLevelDestination
import clbisk.simplestbudget.ui.screens.budgetcategories.create.CreateBudgetCategoryScreen
import clbisk.simplestbudget.ui.screens.budgetcategories.edit.EditBudgetCategoryScreen
import clbisk.simplestbudget.ui.screens.home.HomeScreen
import clbisk.simplestbudget.ui.screens.transactions.create.CreateTransactionScreen
import clbisk.simplestbudget.ui.screens.transactions.edit.EditTransactionScreen
import clbisk.simplestbudget.ui.screens.transactions.list.forcategory.TransactionsForCategoryScreen

val AppScreens: Map<Route, @Composable (navController: NavController) -> Unit> = mapOf(
	Route.Home to {
		HomeScreen(
			navToCreateCategory = {
				navTo(TopLevelDestination.CREATE_CAT, it)(null)
		    },
			navToTransactionsList = {
				cat -> navTo(TopLevelDestination.TRANSACTIONS_FOR_CAT, it)(listOf("$cat"))
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
				cat -> navTo(TopLevelDestination.EDIT_CAT, it)(listOf("$cat"))
			},
			navToEditTransactionRecord = {
				id -> navTo(TopLevelDestination.EDIT_TRANSACTION, it)(listOf("$id"))
			},
			navToCreateTransaction = {
				cat -> navTo(TopLevelDestination.CREATE_TRANSACTION, it)(listOf("$cat"))
			},
		)
	},
)
