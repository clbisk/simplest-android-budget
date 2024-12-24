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

	@Query("SELECT * FROM transactionRecords where inCategoryName = :categoryName")
	fun getTransactionsForCategory(categoryName: String): Flow<List<TransactionRecord>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(category: TransactionRecord)

	@Update
	suspend fun update(category: TransactionRecord)

	@Delete
	suspend fun delete(category: TransactionRecord)
}
