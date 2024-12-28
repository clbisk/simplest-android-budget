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
			parentColumns = ["categoryName"],
			childColumns = ["forCategoryName"],
			onDelete = ForeignKey.CASCADE,
		),
	],
	indices = [
		Index("widgetId"),
		Index("forCategoryName"),
	],
)
data class WidgetModel(
	@PrimaryKey val widgetId: Int,
	val forCategoryName: String,
	val remainingThisMonth: Long,
) : WidgetState
