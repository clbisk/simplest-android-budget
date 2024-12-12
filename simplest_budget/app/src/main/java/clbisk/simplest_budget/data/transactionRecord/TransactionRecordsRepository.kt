package clbisk.simplest_budget.data.transactionRecord

import kotlinx.coroutines.flow.Flow

interface TransactionRecordsRepository {
	fun getTransaction(id: Int): Flow<TransactionRecord>
	fun getAllTransactions(): Flow<List<TransactionRecord>>
	suspend fun insert(transaction: TransactionRecord)
	suspend fun update(transaction: TransactionRecord)
	suspend fun delete(transaction: TransactionRecord)
}
