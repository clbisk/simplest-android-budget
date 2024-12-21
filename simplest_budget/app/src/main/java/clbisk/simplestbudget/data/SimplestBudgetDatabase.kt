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
import clbisk.simplestbudget.widget.model.WidgetModel
import clbisk.simplestbudget.widget.model.WidgetModelDao
import javax.inject.Qualifier

@Database(
	entities = [
		BudgetCategory::class,
		TransactionRecord::class,
		WidgetModel::class,
   ],
	version = 3,
	exportSchema = false,
)
@TypeConverters(TimestampConverters::class)
abstract class SimplestBudgetDatabase : RoomDatabase() {
	abstract fun budgetCategoryDao(): BudgetCategoryDao
	abstract fun transactionRecordDao(): TransactionRecordDao
	abstract fun widgetDao(): WidgetModelDao

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
