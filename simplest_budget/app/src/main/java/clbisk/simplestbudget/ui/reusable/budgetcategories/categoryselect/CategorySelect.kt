package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun CategorySelect(
	onValueSelect: (String) -> Unit,
	enabled: Boolean,
	initialValue: String? = null,
	viewModel: CategorySelectViewModel = hiltViewModel(),
) {
	val input = viewModel.inputState.collectAsState()
	val inputError = viewModel.inputError.collectAsState()
	val isMenuOpen = viewModel.isCategoryMenuOpen.collectAsState()
	val categoryListState = viewModel.categoryListState.collectAsState()
	val categoryList = categoryListState.value.categoryList ?: listOf()

	val focusModifier = Modifier.onFocusChanged {
		if (!it.isFocused) viewModel.openCategoryMenu() else {
			viewModel.onUnfocus()
			viewModel.closeCategoryMenu()
		}
	}

	val clicks = viewModel.clicks.collectAsState()

	Column {
		Row {
			Text("# of clicks: ${clicks.value}")
		}

		Row(
			modifier = Modifier.clickable { viewModel.recordClick() }
		) {
			TextField(
				value = input.value,
				onValueChange = {},
				enabled = enabled,
				readOnly = true,
				label = { Text(stringResource(R.string.category_select_label)) },
				placeholder = { Text(initialValue ?: "") },
				isError = inputError.value,
				singleLine = true,
				trailingIcon = { DropdownArrowIcon(isMenuOpen.value) },
				modifier = focusModifier,
			)

			DropdownMenu(
				expanded = isMenuOpen.value,
				onDismissRequest = viewModel::closeCategoryMenu,
			) {
				categoryList.map {
					DropdownMenuItem(
						text = { Text(it.categoryName) },
						onClick = { onValueSelect(it.categoryName) }
					)
				}
			}
		}4
	}
}
