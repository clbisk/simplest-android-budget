package clbisk.simplest_budget.data

import android.content.Context
import androidx.lifecycle.viewmodel.CreationExtras
import clbisk.simplest_budget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplest_budget.data.budgetCategory.OfflineBudgetCategoriesRepository
import clbisk.simplest_budget.data.transactionRecord.OfflineTransactionRecordsRepository
import clbisk.simplest_budget.data.transactionRecord.TransactionRecordsRepository

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
