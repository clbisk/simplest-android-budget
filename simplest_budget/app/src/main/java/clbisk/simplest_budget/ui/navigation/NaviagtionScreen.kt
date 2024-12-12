package clbisk.simplest_budget.ui.navigation

import clbisk.simplest_budget.R

interface NavigationScreen {
	val route: String
	val titleResourceId: Int
}

object HomeScreen : NavigationScreen {
	override val route = "home"
	override val titleResourceId = R.string.app_name
}

object CreateBudgetCategoryScreen : NavigationScreen {
	override val route = "createCategory"
	override val titleResourceId = R.string.create_category_title
}
