package clbisk.simplestbudget.data.transactionRecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

/** single transaction record in db */
@Entity(tableName = "transactionRecords",
	foreignKeys = [
		ForeignKey(
			entity = BudgetCategory::class,
			parentColumns = ["categoryId"],
			childColumns = ["inCategoryId"],
		)
	], indices = [Index("inCategoryId")]
)
data class TransactionRecord(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "transactionRecordId")
	val id: Int = 0,
	val inCategoryId: Int,
	val currencyAmount: Float,
	val recordedTimestamp: String,
	val description: String = "",
)
