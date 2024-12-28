package clbisk.simplestbudget.ui.reusable.transactions.modify.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R

@Composable
fun CreateTransactionForm (
	name: String,
	limit: Long,
	isValid: Boolean,
	onNameChange: (String) -> Unit,
	onLimitChange: (String) -> Unit,
	onSave: () -> Unit,
	padding: PaddingValues,
) {
	Column(modifier = Modifier.padding(padding)) {
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
