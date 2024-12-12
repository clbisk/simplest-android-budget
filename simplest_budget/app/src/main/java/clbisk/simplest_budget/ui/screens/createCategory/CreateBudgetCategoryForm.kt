package clbisk.simplest_budget.ui.screens.createCategory

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun CreateBudgetCategoryForm(
	navBack: () -> Unit,
	onSave: () -> Unit
) {
	Column {
		OutlinedTextField(
			value = "",
			onValueChange = { onValueChange() }
		)
	}
}

fun onValueChange() {
	TODO("Not yet implemented")
}
