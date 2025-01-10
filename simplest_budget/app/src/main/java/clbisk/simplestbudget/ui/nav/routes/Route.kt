package clbisk.simplestbudget.ui.nav.routes

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
