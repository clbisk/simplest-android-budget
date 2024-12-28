package clbisk.simplestbudget.ui.reusable.budgetcategories.modify.edit

import clbisk.simplestbudget.ui.reusable.budgetcategories.modify.CategoryInput

data class CategoryEditState(
	val stateLoaded: Boolean = false,
	val input: CategoryInput? = null,
)
