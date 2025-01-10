package clbisk.simplestbudget.ui.reusable.transactions.modify

import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import java.util.Date

data class TransactionInput(
	val id: Int? = null,
	val inCategoryId: Int? = null,
	val inCategoryName: String? = null,
	val currencyAmount: String = "",
	val description: String = "",
	val recordedTimestamp: String? = null,
)

/** helper translation fns */
fun TransactionRecord.toTransactionInput(catName: String? = null): TransactionInput {
	return TransactionInput(
		id,
		inCategoryId,
		inCategoryName = catName,
		"$currencyAmount",
		description,
		recordedTimestamp,
	)
}

fun TransactionInput.toTransactionRecord(): TransactionRecord {
	return TransactionRecord(
		id ?: 0,
		inCategoryId!!,
		currencyAmount.toFloat(),
		recordedTimestamp ?: "${Date()}",
		description,
	)
}
