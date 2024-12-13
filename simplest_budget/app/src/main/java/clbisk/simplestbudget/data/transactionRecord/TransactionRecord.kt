package clbisk.simplestbudget.data.transactionRecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import java.sql.Timestamp

/** single transaction record in db */
@Entity(tableName = "transactionRecords",
	foreignKeys = [
		ForeignKey(
			entity = BudgetCategory::class,
			parentColumns = ["categoryName"],
			childColumns = ["categoryId"],
		)
	], indices = [Index("categoryId")]
)
data class TransactionRecord(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "transactionRecordId")
	val id: Int = 0,
	val categoryId: Int,
	val wholeCurrencyAmount: Int = 0,   // for USD: whole dollars
	val fractionalCurrencyAmount: Int = 0,  // for USD: cents
	val recordedTimestamp: Timestamp,
	val description: String = "",
)
