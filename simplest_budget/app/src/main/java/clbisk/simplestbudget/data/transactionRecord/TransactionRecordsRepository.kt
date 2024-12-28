package clbisk.simplestbudget.data.transactionRecord

import kotlinx.coroutines.flow.Flow

interface TransactionRecordsRepository {
	fun getTransaction(id: Int): Flow<TransactionRecord>
	fun getAllTransactions(): Flow<List<TransactionRecord>>
	fun getTransactionsForCategory(categoryName: String): Flow<List<TransactionRecord>>
	fun getTransactionTotalForCategory(categoryName: String): Flow<Long>
	suspend fun insert(transaction: TransactionRecord)
	suspend fun update(transaction: TransactionRecord)
	suspend fun delete(transaction: TransactionRecord)
}
