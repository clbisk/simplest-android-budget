package clbisk.simplest_budget

import android.app.Application
import clbisk.simplest_budget.data.SimplestBudgetAppContainer
import clbisk.simplest_budget.data.SimplestBudgetDataContainer

class SimplestBudgetApp : Application() {
	lateinit var container: SimplestBudgetAppContainer

	override fun onCreate() {
		super.onCreate()
		container = SimplestBudgetDataContainer(this)
	}
}
