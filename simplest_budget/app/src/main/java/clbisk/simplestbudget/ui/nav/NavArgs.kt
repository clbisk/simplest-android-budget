package clbisk.simplestbudget.ui.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

val NavArgs: Map<Route, List<NamedNavArgument>> = mapOf(
	Route.Home to listOf(),
	Route.CreateCategory to listOf(),
	Route.EditCategory to listOf(
		navArgument("categoryName") {
			type = NavType.StringType
		}
	),
	Route.TransactionsForCat to listOf(
		navArgument("categoryName") {
			type = NavType.StringType
		}
	),
	Route.CreateTransaction to listOf(),
	Route.EditTransaction to listOf(
		navArgument("transactionId") {
			type = NavType.IntType
		}
	),
)
