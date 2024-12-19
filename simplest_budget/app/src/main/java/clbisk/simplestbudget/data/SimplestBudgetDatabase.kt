package clbisk.simplestbudget.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoryDao
import clbisk.simplestbudget.data.transactionRecord.TimestampConverters
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordDao
import javax.inject.Qualifier

@Qualifier
annotation class AppCoroutineScope

@Database(
	entities = [BudgetCategory::class, TransactionRecord::class],
	version = 2,
	exportSchema = false
)
@TypeConverters(TimestampConverters::class)
abstract class SimplestBudgetDatabase : RoomDatabase() {
	abstract fun budgetCategoryDao(): BudgetCategoryDao
	abstract fun transactionRecordDao(): TransactionRecordDao

	companion object {
		@Volatile
		private var Instance: SimplestBudgetDatabase? = null

		fun getDatabase(context: Context): SimplestBudgetDatabase {
			return Instance ?: synchronized(this) {
				Room.databaseBuilder(
					context,
					SimplestBudgetDatabase::class.java,
					"simplest_budget_database"
				)
					.fallbackToDestructiveMigration()
					.build()
					.also { Instance = it }
			}
		}
	}
}
