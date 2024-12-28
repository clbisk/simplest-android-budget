package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R
import androidx.compose.foundation.lazy.items as lazyItems

@Composable
fun CategorySelect(
	onValueSelect: (String) -> Unit,
	enabled: Boolean,
	initialValue: String? = null,
	viewModel: CategorySelectViewModel = hiltViewModel(),
) {
	val input = viewModel.inputState.collectAsState()
	val categoryList = viewModel.filteredList
	val inputError = viewModel.inputError.collectAsState()

	var categoryMenuOpen = false
	fun openCategoryMenu() { categoryMenuOpen = true }
	fun closeCategoryMenu() { categoryMenuOpen = false }

	val focusModifier = Modifier.onFocusChanged {
		if (it.isFocused) {
			openCategoryMenu()
		} else {
			viewModel.onUnfocus()
			closeCategoryMenu()
		}
	}

	TextField(
		value = input.value,
		onValueChange = viewModel::onUpdate,
		modifier = focusModifier,
		enabled = enabled,
		readOnly = false,
		label = { Text(stringResource(R.string.category_select_label)) },
		placeholder = { Text(initialValue ?: "") },
		isError = inputError.value,
	)
	
	DropdownMenu(
		expanded = categoryMenuOpen,
		onDismissRequest = { closeCategoryMenu() },
	) {
		LazyColumn {
			lazyItems(items = categoryList, key = { it.categoryName }) {
				DropdownMenuItem(
					text = { Text(it.categoryName) },
					onClick = { onValueSelect(it.categoryName) }
				)
			}
		}
	}
}
