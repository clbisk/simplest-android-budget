package clbisk.simplestbudget.data.transactionRecord

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineTransactionRecordsRepository @Inject constructor(
	private val transactionRecordDao: TransactionRecordDao
) : TransactionRecordsRepository {
	override fun getTransaction(id: Int): Flow<TransactionRecord> =transactionRecordDao.getTransaction(id)
	override fun getAllTransactions(): Flow<List<TransactionRecord>> = transactionRecordDao.getAllTransactions()
	override fun getTransactionsForCategory(categoryName: String): Flow<List<TransactionRecord>> =
		transactionRecordDao.getTransactionsForCategory(categoryName)
	override suspend fun insert(transaction: TransactionRecord) = transactionRecordDao.insert(transaction)
	override suspend fun update(transaction: TransactionRecord) = transactionRecordDao.update(transaction)
	override suspend fun delete(transaction: TransactionRecord) = transactionRecordDao.delete(transaction)
}
