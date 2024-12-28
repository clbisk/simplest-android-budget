package clbisk.simplestbudget.ui.reusable.transactions.modify.create

import clbisk.simplestbudget.ui.reusable.transactions.modify.TransactionInput

data class NewTransactionState(
	val initialCategoryName: String = "",
	val input: TransactionInput = TransactionInput(),
)
