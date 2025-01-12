package clbisk.simplestbudget.data.transactionRecord

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionRecordDao {
	@Query(
		"SELECT * FROM transactionRecords WHERE transactionRecordId = :id"
	)
	fun getTransaction(id: Int): Flow<TransactionRecord>

	@Query("SELECT * FROM transactionRecords")
	fun getAllTransactions(): Flow<List<TransactionRecord>>

	@Query("SELECT * FROM transactionRecords WHERE (inCategoryId = :categoryId AND recordedTimestamp >= (SELECT unixepoch('now','start of month','subsec')) AND recordedTimestamp < (SELECT unixepoch('now','+1 month','start of month','subsec')))")
	fun getTransactionsForCategory(categoryId: Int): Flow<List<TransactionRecord>>

	@Query("SELECT SUM(currencyAmount) FROM transactionRecords WHERE (inCategoryId = :categoryId AND recordedTimestamp >= (SELECT unixepoch('now','start of month','subsec')) AND recordedTimestamp < (SELECT unixepoch('now','+1 month','start of month','subsec')))")
	fun getTransactionTotalForCategory(categoryId: Int): Flow<Float>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(category: TransactionRecord)

	@Update
	suspend fun update(category: TransactionRecord)

	@Delete
	suspend fun delete(category: TransactionRecord)
}
