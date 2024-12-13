package clbisk.simplest_budget.ui.navigation

import clbisk.simplest_budget.R

interface NavigationRoute {
	val routeId: String
	val titleResourceId: Int
}

object HomeRoute : NavigationRoute {
	override val routeId = "home"
	override val titleResourceId = R.string.app_name
}

object CreateBudgetCategoryRoute : NavigationRoute {
	override val routeId = "createCategory"
	override val titleResourceId = R.string.create_category_title
}
