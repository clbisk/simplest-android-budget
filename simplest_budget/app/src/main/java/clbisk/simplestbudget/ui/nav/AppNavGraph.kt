package clbisk.simplestbudget.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(
	modifier: Modifier = Modifier,
) {
	val navController = rememberNavController()

	AppNavSuite(
		modifier = modifier,
	) {
		NavHost(
			navController,
			startDestination = START_DESTINATION.name,
		) {
			TopLevelDestination.entries.map { dest ->
				val destArgs = NavArgs[dest.route]

				val argNames = if (!destArgs.isNullOrEmpty()) {
					"/${destArgs.forEach { "{${it.name}}" }}"
				} else ""
				val route = dest.name + argNames

				val destScreen = AppScreens[dest.route]
					?: throw Exception("NAV GRAPH ERROR -- Screen for ${dest.name} not specified!")

				composable(route) {
					destScreen(navController)
				}
			}
		}
	}
}
