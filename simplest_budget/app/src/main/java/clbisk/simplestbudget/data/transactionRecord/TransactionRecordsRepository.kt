package clbisk.simplestbudget.data.transactionRecord

import kotlinx.coroutines.flow.Flow

interface TransactionRecordsRepository {
	fun getTransaction(id: Int): Flow<TransactionRecord>
	fun getAllTransactions(): Flow<List<TransactionRecord>>
	fun getTransactionsForCategory(categoryId: Int): Flow<List<TransactionRecord>>
	fun getTransactionTotalForCategory(categoryId: Int): Flow<Float>
	suspend fun insert(transaction: TransactionRecord)
	suspend fun update(transaction: TransactionRecord)
	suspend fun delete(transaction: TransactionRecord)
}
