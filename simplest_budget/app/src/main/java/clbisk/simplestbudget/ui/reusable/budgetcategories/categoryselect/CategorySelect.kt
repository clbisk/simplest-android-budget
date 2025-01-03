package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R

@Composable
fun CategorySelect(
	onValueSelect: (String) -> Unit,
	enabled: Boolean,
	initialValue: String? = null,
	viewModel: CategorySelectViewModel = hiltViewModel(),
) {
	val input = viewModel.inputState.collectAsState()
	val inputError = viewModel.inputError.collectAsState()
	val categoryListState = viewModel.categoryListState.collectAsState()
	val categoryList = categoryListState.value.categoryList ?: listOf()

	var isMenuOpen by remember { mutableStateOf(false) }

	fun openCategoryMenu() { isMenuOpen = true }
	fun closeCategoryMenu() {
		isMenuOpen = false
		if (input.value.isNotEmpty()) {
			viewModel.onUnfocus()
		}
	}

	val focusModifier = Modifier.onFocusChanged {
		if (!it.isFocused) openCategoryMenu()
		else { closeCategoryMenu() }
	}


	Box {
		TextField(
			value = input.value,
			onValueChange = {},
			enabled = enabled,
			label = { Text(stringResource(R.string.category_select_label)) },
			placeholder = { Text(initialValue ?: "") },
			isError = inputError.value,
			singleLine = true,
			trailingIcon = { DropdownArrowIcon(isMenuOpen) },
			modifier = focusModifier,
		)

		DropdownMenu(
			expanded = isMenuOpen,
			onDismissRequest = { isMenuOpen = false },
		) {
			categoryList.map {
				DropdownMenuItem(
					text = { Text(it.categoryName) },
					onClick = { onValueSelect(it.categoryName) }
				)
			}
		}
	}
}
