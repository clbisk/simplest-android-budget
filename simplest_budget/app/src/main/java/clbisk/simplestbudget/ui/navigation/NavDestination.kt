package clbisk.simplestbudget.ui.navigation

import clbisk.simplestbudget.R

interface NavDestination {
	val route: String
	val titleResourceId: Int
}

object HomeDestination : NavDestination {
	override val route = "home"
	override val titleResourceId = R.string.app_name
}

object CreateBudgetCategoryDestination : NavDestination {
	override val route = "createCategory"
	override val titleResourceId = R.string.create_category_title
}
