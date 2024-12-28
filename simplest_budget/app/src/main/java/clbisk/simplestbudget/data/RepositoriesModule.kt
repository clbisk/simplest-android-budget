package clbisk.simplestbudget.data

import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.OfflineBudgetCategoriesRepository
import clbisk.simplestbudget.data.transactionRecord.OfflineTransactionRecordsRepository
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
	@Binds
	fun providesBudgetCategoriesRepository(repository: OfflineBudgetCategoriesRepository): BudgetCategoriesRepository

	@Binds
	fun providesTransactionRecordsRepository(repository: OfflineTransactionRecordsRepository): TransactionRecordsRepository
}
