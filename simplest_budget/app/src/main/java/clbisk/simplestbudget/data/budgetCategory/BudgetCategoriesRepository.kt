package clbisk.simplestbudget.data.budgetCategory

import kotlinx.coroutines.flow.Flow

interface BudgetCategoriesRepository {
	fun getCategory(name: String): Flow<BudgetCategory>
	fun getAllCategories(): Flow<List<BudgetCategory>>
	suspend fun insert(category: BudgetCategory)
	suspend fun update(category: BudgetCategory)
	suspend fun delete(category: BudgetCategory)
}
