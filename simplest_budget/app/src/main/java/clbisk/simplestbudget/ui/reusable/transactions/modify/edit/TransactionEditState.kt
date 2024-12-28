package clbisk.simplestbudget.ui.reusable.transactions.modify.edit

import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput

data class TransactionEditState(
	val stateLoaded: Boolean = false,
	val initialCategoryName: String? = null,
	val input: TransactionInput? = null,
)
