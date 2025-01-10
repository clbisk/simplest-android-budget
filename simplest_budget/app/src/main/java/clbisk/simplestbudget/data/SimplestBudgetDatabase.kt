package clbisk.simplestbudget.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoryDao
import clbisk.simplestbudget.data.transactionRecord.TimestampConverters
import clbisk.simplestbudget.data.transactionRecord.TransactionRecord
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordDao
import clbisk.simplestbudget.widget.data.WidgetModel
import clbisk.simplestbudget.widget.data.WidgetModelDao

@Database(
	entities = [
		BudgetCategory::class,
		TransactionRecord::class,
		WidgetModel::class,
   ],
	version = 10,
	exportSchema = false,
)
@TypeConverters(TimestampConverters::class)
abstract class SimplestBudgetDatabase : RoomDatabase() {
	abstract fun budgetCategoryDao(): BudgetCategoryDao
	abstract fun transactionRecordDao(): TransactionRecordDao
	abstract fun widgetDao(): WidgetModelDao
}
