package clbisk.simplestbudget.ui.budgetcategories.modify.edit

import clbisk.simplestbudget.ui.budgetcategories.modify.CategoryInput

data class CategoryEditState(
	val stateLoaded: Boolean = false,
	val input: CategoryInput? = null,
)
