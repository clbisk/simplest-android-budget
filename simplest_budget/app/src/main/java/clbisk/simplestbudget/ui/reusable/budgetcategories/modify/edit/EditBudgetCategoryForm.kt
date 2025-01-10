package clbisk.simplestbudget.ui.reusable.budgetcategories.modify.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R

@Composable
fun EditBudgetCategoryForm (
	name: String,
	limit: Float,
	isLoaded: Boolean,
	onNameInputChange: (String) -> Unit,
	onLimitInputChange: (String) -> Unit,
	onSave: () -> Unit
) {
	Column {
		Column {
			OutlinedTextField(
				value = name,
				onValueChange = onNameInputChange,
				enabled = isLoaded,
			)
			OutlinedTextField(
				value = limit.toString(),
				onValueChange = onLimitInputChange,
				enabled = isLoaded,
			)
		}
		Button(
			onClick = onSave,
			enabled = isLoaded,
			shape = MaterialTheme.shapes.small
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
