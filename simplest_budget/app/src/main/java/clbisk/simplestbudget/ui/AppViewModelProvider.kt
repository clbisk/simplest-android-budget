package clbisk.simplestbudget.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import clbisk.simplestbudget.SimplestBudgetApp
import clbisk.simplestbudget.ui.budgetcategories.list.CategoryListViewModel
import clbisk.simplestbudget.ui.budgetcategories.modify.edit.EditCategoryViewModel
import clbisk.simplestbudget.ui.budgetcategories.modify.create.NewCategoryViewModel

/** provide budget app instance to viewModel factory */
fun CreationExtras.budgetApp(): SimplestBudgetApp =
	(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SimplestBudgetApp)

/**
 * factory for viewModels
 * provides relevant data repos
 * */
object AppViewModelProvider {
	val Factory = viewModelFactory {
		// category list viewModel
		initializer {
			CategoryListViewModel(budgetApp().container.budgetCategoriesRepository)
		}

		// new category viewModel
		initializer {
			NewCategoryViewModel(budgetApp().container.budgetCategoriesRepository)
		}

		// edit category viewModel
		initializer {
			EditCategoryViewModel(
				this.createSavedStateHandle(),
				budgetApp().container.budgetCategoriesRepository
			)
		}
	}
}
