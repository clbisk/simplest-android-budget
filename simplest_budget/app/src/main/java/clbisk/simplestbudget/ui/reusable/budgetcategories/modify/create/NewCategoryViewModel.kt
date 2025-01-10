package clbisk.simplestbudget.ui.reusable.budgetcategories.modify.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clbisk.simplestbudget.data.budgetCategory.BudgetCategoriesRepository
import clbisk.simplestbudget.data.budgetCategory.BudgetCategory
import clbisk.simplestbudget.ui.reusable.util.parseStringAsCurrencyFloat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewCategoryViewModel @Inject constructor(
	private val categoryRepository: BudgetCategoriesRepository
) : ViewModel() {
	private val _nameInput = MutableLiveData("")
	private val _limitInput = MutableLiveData(0.toFloat())
	private val _inputValid = MutableLiveData(false)

	val categoryName: LiveData<String> = _nameInput
	val spendingLimit: LiveData<Float> = _limitInput
	val isValid: LiveData<Boolean> = _inputValid

	fun onNameUpdate(name: String) {
		_nameInput.value = name
		_inputValid.value = validateInput()
	}

	fun onLimitUpdate(limitAsStr: String) {
		val limit = parseStringAsCurrencyFloat(limitAsStr)
		if (limit != null) {
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
