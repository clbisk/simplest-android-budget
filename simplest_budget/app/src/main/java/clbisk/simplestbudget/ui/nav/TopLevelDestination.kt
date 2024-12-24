package clbisk.simplestbudget.ui.nav

import kotlinx.serialization.Serializable

sealed interface Route {
	@Serializable
	data object Home : Route

	@Serializable
	data object CreateCategory : Route

	@Serializable
	data object EditCategory : Route

	@Serializable
	data object TransactionsForCat : Route

	@Serializable
	data object CreateTransaction : Route

	@Serializable
	data object EditTransaction : Route
}

enum class TopLevelDestination(
	val route: Route,
) {
	Home(Route.Home),
	CreateCategory(Route.CreateCategory),
	EditCategory(Route.EditCategory),
	TransactionsForCat(Route.TransactionsForCat),
	CreateTransaction(Route.CreateTransaction),
	EditTransaction(Route.EditTransaction),
}
