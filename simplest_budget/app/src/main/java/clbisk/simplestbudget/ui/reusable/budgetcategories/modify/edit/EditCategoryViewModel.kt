package clbisk.simplestbudget.ui.reusable.budgetcategories.modify.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.data.transactionRecord.TransactionRecordsRepository
import clbisk.simplestbudget.ui.reusable.model.CategoryInput
import clbisk.simplestbudget.ui.reusable.model.toBudgetCategory
import clbisk.simplestbudget.ui.reusable.model.toCategoryInput
import clbisk.simplestbudget.ui.reusable.model.CategoryEditState
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyLong
import clbisk.simplestbudget.widget.data.WidgetModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val categoryRepository: BudgetCategoriesRepository,
	private val widgetRepository: WidgetModelRepository,
	private val transactionRecordsRepository: TransactionRecordsRepository,
) : ViewModel() {
	/** read category name to be loaded from navigation input */
	private val nameArg: String = checkNotNull(savedStateHandle["categoryName"])
	/** store original values of the specified category before edits */
	private lateinit var initialBudgetCategory: BudgetCategory

	val inputState: MutableStateFlow<CategoryEditState> = MutableStateFlow(CategoryEditState())

	init {
		viewModelScope.launch {
			initialBudgetCategory = categoryRepository.getCategory(nameArg)
				.filterNotNull()
				.first()

			inputState.update {
				it.copy(
					stateLoaded = true,
					input = initialBudgetCategory.toCategoryInput()
				)
			}
		}
	}

	private fun updateInput(newInput: CategoryInput?) {
		if (!inputState.value.stateLoaded || newInput == null) {
			throw Exception("cannot edit category before loading previous value")
		}

		inputState.update {
			it.copy(input = newInput)
		}
	}

	fun onNameInputChange(name: String) {
		updateInput(inputState.value.input?.copy(categoryName = name))
	}

	fun onLimitInputChange(limitStr: String) {
		val limit = parseStringAsCurrencyLong(limitStr)
		if (limit != null) {
			updateInput(inputState.value.input?.copy(spendingLimit = limit))
		}
	}

	private fun validateInput(): Boolean {
		return inputState.value.input!!.categoryName.isNotBlank()
	}

	suspend fun saveEditedCategory() {
		val editedCategory = inputState.value.input
			?: throw Exception("cannot save null category input")

		if (validateInput()) {
			// check if any changes were made
			val nameChanged =
				editedCategory.categoryName != initialBudgetCategory.categoryName
			val limitChanged =
				editedCategory.spendingLimit != initialBudgetCategory.spendingLimit

			if (nameChanged || limitChanged) {
				if (!nameChanged) {
					// if name was not changed, update the original category
					categoryRepository.update(editedCategory.toBudgetCategory())
				} else {
					// if the name was changed, first delete the old category
					categoryRepository.delete(initialBudgetCategory)
					// then insert a new category
					categoryRepository.insert(editedCategory.toBudgetCategory())
				}

				// send updates to widget
				val prevWidgetCategory = initialBudgetCategory.categoryName
				val spentThisMonth = transactionRecordsRepository
					.getTransactionTotalForCategory(editedCategory.categoryName)
					.first()

				widgetRepository.updateBudgetForCategory(
					categoryName = editedCategory.categoryName,
					limit = editedCategory.spendingLimit,
					remaining = editedCategory.spendingLimit - spentThisMonth,
					prevCategoryName = prevWidgetCategory,
				)
			}
		}
	}
}
