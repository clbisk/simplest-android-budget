package clbisk.simplest_budget.data.budgetCategory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** single budget category record in db */
@Entity(tableName = "budgetCategories")
data class BudgetCategory(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "budgetCategoryId")
	val id: Int = 0,
	val categoryName: String,
	val spendingLimit: Int,
)
