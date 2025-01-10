package clbisk.simplestbudget.ui.nav.screens

import androidx.navigation.NavController
import clbisk.simplestbudget.ui.nav.args.RouteNavArgs
import clbisk.simplestbudget.ui.nav.routes.TopLevelDestination

fun navTo(toDest: TopLevelDestination, navController: NavController): (args: List<String>?) -> Unit = {
		args ->
	val routeName =
		if (RouteNavArgs[toDest.route]?.isEmpty() == false)
			"${toDest.name}/${args!!.joinToString(separator = ",")}"
		else toDest.name

	navController.navigate(routeName)
}
