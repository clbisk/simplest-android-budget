package clbisk.simplestbudget.ui.reusable.budgetcategories.modify.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import clbisk.simplestbudget.R
import clbisk.simplestbudget.ui.reusable.util.getDeviceCurrencySymbol

@Composable
fun EditBudgetCategoryForm (
	name: String,
	limit: Float,
	isLoaded: Boolean,
	onNameInputChange: (String) -> Unit,
	onLimitInputChange: (String) -> Unit,
	onSave: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Column(
			modifier = Modifier.padding(bottom = 20.dp),
		) {
			OutlinedTextField(
				value = name,
				onValueChange = onNameInputChange,
				enabled = isLoaded,
				label = { Text(stringResource(R.string.category_name_input_label)) },
			)
			OutlinedTextField(
				value = limit.toString(),
				onValueChange = onLimitInputChange,
				enabled = isLoaded,
				label = { Text(stringResource(R.string.category_budget_input_label)) },
				leadingIcon = { Text(getDeviceCurrencySymbol()) },
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
