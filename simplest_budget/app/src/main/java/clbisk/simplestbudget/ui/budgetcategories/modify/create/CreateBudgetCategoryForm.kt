package clbisk.simplestbudget.ui.budgetcategories.modify.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R

@Composable
fun CreateBudgetCategoryForm (
	name: String,
	limit: Int,
	isValid: Boolean,
	onNameChange: (String) -> Unit,
	onLimitChange: (String) -> Unit,
	onSave: () -> Unit
) {
	Column {
		Column {
			OutlinedTextField(
				value = name,
				onValueChange = onNameChange
			)
			OutlinedTextField(
				value = limit.toString(),
				onValueChange = onLimitChange
			)
		}
		Button(
			onClick = onSave,
			enabled = isValid,
			shape = MaterialTheme.shapes.small
		) {
			Text(stringResource(R.string.save_button_text))
		}
	}
}
