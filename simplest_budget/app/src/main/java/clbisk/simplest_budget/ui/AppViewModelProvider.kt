package clbisk.simplest_budget.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import clbisk.simplest_budget.SimplestBudgetApp
import clbisk.simplest_budget.ui.budgetCategories.CategoryInputViewModel
import clbisk.simplest_budget.ui.budgetCategories.budget_category_list.CategoryListViewModel

fun CreationExtras.budgetApp(): SimplestBudgetApp =
	(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SimplestBudgetApp)

object AppViewModelProvider {
	val Factory = viewModelFactory {
		initializer {
			CategoryListViewModel(budgetApp().container.budgetCategoriesRepository)
		}
	}
}
