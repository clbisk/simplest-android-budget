package clbisk.simplestbudget.ui.reusable.transactions.modify

import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import java.sql.Timestamp
import java.util.Date

data class TransactionInput(
	val id: Int? = null,
	val inCategoryName: String = "",
	val currencyAmount: String = "",
	val description: String = "",
	val recordedTimestamp: Timestamp? = null,
)

/** helper translation fns */
fun TransactionRecord.toTransactionInput(): TransactionInput {

	return TransactionInput(
		id,
		inCategoryName,
		"$currencyAmount",
		description,
		recordedTimestamp,
	)
}

fun TransactionInput.toTransactionRecord(): TransactionRecord {
	if (id == null)
		throw Exception("ERROR PARSING TRANSACTION INPUT: can not update a transaction with no id")

	return TransactionRecord(
		id,
		inCategoryName,
		currencyAmount.toLong(),
		recordedTimestamp ?: Timestamp(Date().time),
		description,
	)
}
