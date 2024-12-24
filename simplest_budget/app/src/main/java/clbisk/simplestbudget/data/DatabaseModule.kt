package clbisk.simplestbudget.data

import android.content.Context
import androidx.room.Room
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoryDao
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordDao
import clbisk.simplestbudget.widget.data.WidgetModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher

@Qualifier
annotation class AppCoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
	@Provides
	@Singleton
	fun providesAppDatabase(@ApplicationContext context: Context): SimplestBudgetDatabase =
		Room.databaseBuilder(context, SimplestBudgetDatabase::class.java, "app.db")
			.fallbackToDestructiveMigration()
			.build()

	@Provides
	fun providesWidgetModelDao(database: SimplestBudgetDatabase): WidgetModelDao = database.widgetDao()

	@Provides
	fun providesBudgetCategoryDao(database: SimplestBudgetDatabase): BudgetCategoryDao = database.budgetCategoryDao()

	@Provides
	fun providesTransactionRecordDao(database: SimplestBudgetDatabase): TransactionRecordDao = database.transactionRecordDao()

	@Provides
	@Singleton
	@AppCoroutineScope
	fun providesApplicationCoroutineScope(): CoroutineScope = CoroutineScope(
		// TODO: is this the right executor??
		Executors.newSingleThreadExecutor().asCoroutineDispatcher()
	)
}
