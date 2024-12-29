package clbisk.simplestbudget.ui.reusable.model

import clbisk.simplestbudget.data.transactionRecord.TransactionRecord

data class TransactionListState(
	val transactionList: List<TransactionRecord>? = null
)
