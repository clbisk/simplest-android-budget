package clbisk.simplestbudget.data

import android.content.Context
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.OfflineBudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.OfflineTransactionRecordsRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository

interface SimplestBudgetAppContainer {
	val budgetCategoriesRepository: BudgetCategoriesRepository
	val transactionRecordsRepository: TransactionRecordsRepository
}

class SimplestBudgetDataContainer(private val context: Context) : SimplestBudgetAppContainer {
	override val budgetCategoriesRepository: BudgetCategoriesRepository by lazy {
		OfflineBudgetCategoriesRepository(SimplestBudgetDatabase.getDatabase(context).budgetCategoryDao())
	}
	override val transactionRecordsRepository: TransactionRecordsRepository by lazy {
		OfflineTransactionRecordsRepository(SimplestBudgetDatabase.getDatabase(context).transactionRecordDao())
	}
}
