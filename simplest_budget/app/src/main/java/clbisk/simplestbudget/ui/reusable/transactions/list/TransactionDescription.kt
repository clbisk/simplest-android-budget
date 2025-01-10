package clbisk.simplestbudget.ui.reusable.transactions.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable fun TransactionDescription(
	text: String? = null
) {
	if (text != null) {
		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text,
				style = MaterialTheme.typography.bodyMedium
			)
		}
	}
}