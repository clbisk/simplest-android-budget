package clbisk.simplestbudget.ui.reusable.transactions.modify

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect.SimpleCategorySelect

@Composable
fun ModifyTransactionFormInputs(
	enabled: Boolean = false,
	currencySymbol: String = "$",
	categoryName: String = "",
	transactionAmtInput: String = "",
	description: String = "",
	updateCategory: (Int, String) -> Unit,
	updateTransactionAmtInput: (String) -> Unit,
	updateDescription: (String) -> Unit,
) {
	SimpleCategorySelect(
		onValueSelect = updateCategory,
		enabled = enabled,
		initialValue = categoryName,
	)

	/** transaction amount input **/
	Row {
		TextField(
			value = transactionAmtInput,
			onValueChange = updateTransactionAmtInput,
			enabled = enabled,
			label = { Text("Amount") },
			leadingIcon = { Text(currencySymbol) },
		)
	}

	/** description input **/
	Row {
		TextField(
			value = description,
			onValueChange = updateDescription,
			enabled = enabled,
			label = { Text("Description (optional)") },
			minLines = 5,
		)
	}
}
