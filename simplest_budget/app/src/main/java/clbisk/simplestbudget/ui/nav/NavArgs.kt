package clbisk.simplestbudget.ui.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

val NavArgs: Map<Route, List<NamedNavArgument>> = mapOf(
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
	Route.EditTransaction to listOf(
		navArgument("transactionId") {
			type = NavType.IntType
		}
	),
)
