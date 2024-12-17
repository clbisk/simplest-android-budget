package clbisk.simplestbudget.ui.budgetcategories.modify.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.parseStringAsInt

class NewCategoryViewModel(
	private val categoryRepository: BudgetCategoriesRepository
) : ViewModel() {
	private val _nameInput = MutableLiveData("")
	private val _limitInput = MutableLiveData(0)
	private val _inputValid = MutableLiveData(false)

	val categoryName: LiveData<String> = _nameInput
	val spendingLimit: LiveData<Int> = _limitInput
	val isValid: LiveData<Boolean> = _inputValid

	fun onNameUpdate(name: String) {
		if (name.isNotBlank()) {
			_nameInput.value = name
		}
		_inputValid.value = validateInput()
	}

	fun onLimitUpdate(limitAsStr: String) {
		val limit = parseStringAsInt(limitAsStr)
		if (limit !== null) {
			_limitInput.value = limit
		}
		_inputValid.value = validateInput()
	}

	suspend fun saveNewCategory() {
		categoryRepository.insert(BudgetCategory(
			categoryName = categoryName.value!!,
			spendingLimit = _limitInput.value!!,
		))
	}

	private fun validateInput(): Boolean {
		return !(_nameInput.value.isNullOrBlank())
	}
}