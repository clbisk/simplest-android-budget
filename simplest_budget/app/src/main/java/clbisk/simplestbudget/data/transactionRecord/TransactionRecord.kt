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
			childColumns = ["inCategoryName"],
		)
	], indices = [Index("inCategoryName")]
)
data class TransactionRecord(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "transactionRecordId")
	val id: Int = 0,
	val inCategoryName: String,
	val currencyAmount: Long,
	val recordedTimestamp: Timestamp,
	val description: String = "",
)
