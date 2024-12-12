package clbisk.simplest_budget.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import clbisk.simplest_budget.data.budgetCategory.BudgetCategory
import clbisk.simplest_budget.data.budgetCategory.BudgetCategoryDao
import clbisk.simplest_budget.data.transactionRecord.TimestampConverters
import clbisk.simplest_budget.data.transactionRecord.TransactionRecord
import clbisk.simplest_budget.data.transactionRecord.TransactionRecordDao

@Database(
	entities = [BudgetCategory::class, TransactionRecord::class],
	version = 1,
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
