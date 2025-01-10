package clbisk.simplestbudget.ui.nav.args

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import clbisk.simplestbudget.ui.nav.routes.Route

val RouteNavArgs: Map<Route, List<NamedNavArgument>> = mapOf(
	Route.EditCategory to listOf(
		navArgument(NavArgs.CAT_ID.name) {}
	),
	Route.TransactionsForCat to listOf(
		navArgument(NavArgs.CAT_ID.name) {}
	),
	Route.CreateTransaction to listOf(
		navArgument(NavArgs.CAT_ID.name) {}
	),
	Route.EditTransaction to listOf(
		navArgument(NavArgs.TRANSACTION_ID.name) {}
	),
)
