package clbisk.simplest_budget.data.budgetCategory

import kotlinx.coroutines.flow.Flow

class OfflineBudgetCategoriesRepository(
	private val budgetCategoryDao: BudgetCategoryDao
) : BudgetCategoriesRepository {
	override fun getCategory(id: Int): Flow<BudgetCategory> = budgetCategoryDao.getCategory(id)
	override fun getAllCategories(): Flow<List<BudgetCategory>> = budgetCategoryDao.getAllCategories()
	override suspend fun insert(category: BudgetCategory) = budgetCategoryDao.insert(category)
	override suspend fun update(category: BudgetCategory) = budgetCategoryDao.update(category)
	override suspend fun delete(category: BudgetCategory) = budgetCategoryDao.delete(category)
}
