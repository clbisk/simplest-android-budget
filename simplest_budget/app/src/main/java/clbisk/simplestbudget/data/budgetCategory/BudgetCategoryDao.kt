package clbisk.simplestbudget.data.budgetCategory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetCategoryDao {
	@Query("SELECT * from budgetCategories WHERE categoryId = :id")
	fun getCategory(id: Int): Flow<BudgetCategory>

	@Query("SELECT * from budgetCategories")
	fun getAllCategories(): Flow<List<BudgetCategory>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(category: BudgetCategory)

	@Update
	suspend fun update(category: BudgetCategory)

	@Delete
	suspend fun delete(category: BudgetCategory)
}
