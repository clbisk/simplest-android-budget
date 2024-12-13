package clbisk.simplestbudget.ui.budgetcategories.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.budgetcategories.BudgetCategoryInput

@Composable
fun CategoryInputs(
	input: BudgetCategoryInput,
	onChange: (BudgetCategoryInput) -> Unit
) {
	Column {
		OutlinedTextField(
			value = input.name,
			onValueChange = { onChange(input.copy(name = it)) }
		)
	}
}
