package clbisk.simplestbudget.ui.budgetcategories.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.budgetcategories.BudgetCategoryInput
import clbisk.simplestbudget.ui.budgetcategories.BudgetCategoryInputState

@Composable
fun CreateBudgetCategoryForm (
	inputState: BudgetCategoryInputState,
	onChange: (BudgetCategoryInput) -> Unit,
	onSave: () -> Unit
) {
	Column {
		CategoryInputs(
			input = inputState.category,
			onChange = onChange
		)
		Button(
			onClick = onSave,
			enabled = inputState.isEntryValid,
			shape = MaterialTheme.shapes.small
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
