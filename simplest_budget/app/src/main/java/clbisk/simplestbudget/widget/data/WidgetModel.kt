package clbisk.simplestbudget.widget.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory

sealed interface WidgetState {
	data object Empty : WidgetState
	data object Loading : WidgetState
}

@Entity(
	foreignKeys = [
		ForeignKey(
			entity = BudgetCategory::class,
			parentColumns = ["categoryId"],
			childColumns = ["forCategoryId"],
			onDelete = ForeignKey.CASCADE,
		),
	],
	indices = [
		Index("widgetId"),
		Index("forCategoryId"),
	],
)
data class WidgetModel(
	@PrimaryKey val widgetId: Int,
	val forCategoryId: Int,
	val forCategoryName: String,
	val spendingLimit: Float,
	val remainingThisMonth: Float,
) : WidgetState
