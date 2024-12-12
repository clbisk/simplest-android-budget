package clbisk.simplest_budget.data.transactionRecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import clbisk.simplest_budget.data.budgetCategory.BudgetCategory
import java.sql.Time
import java.sql.Timestamp

/** single transaction record in db */
@Entity(tableName = "transactionRecords",
	foreignKeys = [
		ForeignKey(
			entity = BudgetCategory::class,
			parentColumns = ["budgetCategoryId"],
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
