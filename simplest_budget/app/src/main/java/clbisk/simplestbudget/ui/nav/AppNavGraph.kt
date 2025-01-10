package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import clbisk.simplestbudget.ui.nav.args.RouteNavArgs
import clbisk.simplestbudget.ui.nav.routes.START_DESTINATION
import clbisk.simplestbudget.ui.nav.routes.TopLevelDestination
import clbisk.simplestbudget.ui.nav.screens.AppScreens

@Composable
fun AppNavGraph() {
	val navController = rememberNavController()
	NavHost(
		navController,
		startDestination = START_DESTINATION.name,
	) {
		TopLevelDestination.entries.map { dest ->
			val destArgs = RouteNavArgs[dest.route] ?: listOf()
			val argNames = if (destArgs.isNotEmpty()) {
				val allNames = destArgs.map { "{${it.name}}" }
				"/${allNames.joinToString(separator = "")}"
			} else ""

			val route = dest.name + argNames
			val destScreen = AppScreens[dest.route]
				?: throw Exception("NAV GRAPH ERROR -- Screen for ${dest.name} not specified!")

			composable(route, destArgs) {
				destScreen(navController)
			}
		}
	}
}
