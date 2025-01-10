package clbisk.simplestbudget.ui.nav.routes

enum class TopLevelDestination(val route: Route) {
	HOME(Route.Home),
	CREATE_CAT(Route.CreateCategory),
	EDIT_CAT(Route.EditCategory),
	TRANSACTIONS_FOR_CAT(Route.TransactionsForCat),
	CREATE_TRANSACTION(Route.CreateTransaction),
	EDIT_TRANSACTION(Route.EditTransaction),
}

val START_DESTINATION = TopLevelDestination.HOME
