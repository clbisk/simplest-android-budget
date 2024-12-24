package clbisk.simplestbudget.widget.data

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import clbisk.simplestbudget.data.AppCoroutineScope
import clbisk.simplestbudget.widget.SimplestBudgetWidget
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetModelRepository @Inject internal constructor(
	private val widgetModelDao: WidgetModelDao,
	@AppCoroutineScope private val coroutineScope: CoroutineScope,
	@ApplicationContext private val appContext: Context,
) {
	@EntryPoint
	@InstallIn(SingletonComponent::class)
	interface WidgetModelRepositoryEntrypoint {
		fun widgetModelRepository(): WidgetModelRepository
	}

	companion object {
		fun get(applicationContext: Context): WidgetModelRepository {
			val widgetModelRepositoryEntrypoint: WidgetModelRepositoryEntrypoint = EntryPoints.get(
				applicationContext,
				WidgetModelRepositoryEntrypoint::class.java,
			)
			return widgetModelRepositoryEntrypoint.widgetModelRepository()
		}
	}

	suspend fun createOrUpdate(model: WidgetModel): WidgetModel? {
		val maybeModel = widgetModelDao.loadWidgetModel(model.widgetId).first()
		if (maybeModel == null) {
			widgetModelDao.insert(model)
		} else {
			widgetModelDao.update(model)
		}
		SimplestBudgetWidget().updateAll(appContext)
		return widgetModelDao.loadWidgetModel(model.widgetId).first()
	}

	fun loadModel(widgetId: Int): Flow<WidgetState> {
		return widgetModelDao.loadWidgetModel(widgetId).map {
			it ?: WidgetState.Empty
		}.distinctUntilChanged()
	}

	fun cleanupWidgetModels(context: Context) {
		coroutineScope.launch {
			val widgetManager = GlanceAppWidgetManager(context)
			val widgetIds = widgetManager.getGlanceIds(SimplestBudgetWidget::class.java).map {
				glanceId -> widgetManager.getAppWidgetId(glanceId)
			}.toList()

			widgetModelDao.findOrphanModels(widgetIds).forEach { model ->
				widgetModelDao.delete(model)
			}
		}
	}

	// TODO: use
	fun updateBudgetForCategory(categoryName: String, remainingThisMonthDollars: Int, remainingThisMonthCents: Int) {
		coroutineScope.launch {
			widgetModelDao.modelsForCategory(categoryName).forEach { model ->
				if (model != null) {
					widgetModelDao.update(
						WidgetModel(
							model.widgetId,
							model.forCategoryName,
							remainingThisMonthDollars,
							remainingThisMonthCents,
						),
					)
					SimplestBudgetWidget().updateAll(appContext)
				}
			}
		}
	}
}
