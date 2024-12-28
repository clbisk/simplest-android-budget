package clbisk.simplestbudget.data.budgetCategory

import androidx.room.Entity
import androidx.room.PrimaryKey

/** single budget category record in db */
@Entity(tableName = "budgetCategories")
data class BudgetCategory(
	@PrimaryKey(autoGenerate = false)
	val categoryName: String,
	val spendingLimit: Long,
)
