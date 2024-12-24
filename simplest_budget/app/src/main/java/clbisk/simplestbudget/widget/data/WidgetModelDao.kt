package clbisk.simplestbudget.widget.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetModelDao {
	@Query("SELECT * FROM WidgetModel where widgetId = :widgetId")
	fun loadWidgetModel(widgetId: Int): Flow<WidgetModel?>

	@Query("SELECT * FROM WidgetModel where widgetId NOT IN (:widgetIds)")
	fun findOrphanModels(widgetIds: List<Int>): List<WidgetModel>

	@Query("SELECT * FROM WidgetModel where forCategoryName = :categoryName")
	fun modelsForCategory(categoryName: String): List<WidgetModel?>

	@Insert
	suspend fun insert(model: WidgetModel)

	@Update
	suspend fun update(model: WidgetModel)

	@Delete
	suspend fun delete(model: WidgetModel)
}
