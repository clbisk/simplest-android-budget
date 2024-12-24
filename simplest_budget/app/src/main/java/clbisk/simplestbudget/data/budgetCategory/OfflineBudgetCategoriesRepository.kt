package clbisk.simplestbudget.data.budgetCategory

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineBudgetCategoriesRepository @Inject internal constructor(
	private val budgetCategoryDao: BudgetCategoryDao
) : BudgetCategoriesRepository {
	override fun getCategory(name: String): Flow<BudgetCategory> = budgetCategoryDao.getCategory(name)
	override fun getAllCategories(): Flow<List<BudgetCategory>> = budgetCategoryDao.getAllCategories()
	override suspend fun insert(category: BudgetCategory) = budgetCategoryDao.insert(category)
	override suspend fun update(category: BudgetCategory) = budgetCategoryDao.update(category)
	override suspend fun delete(category: BudgetCategory) = budgetCategoryDao.delete(category)
}
