package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import clbisk.simplestbudget.R

@Composable
fun SimpleCategorySelect(
	onValueSelect: (Int, String) -> Unit,
	enabled: Boolean = false,
	initialValue: String = "",
	viewModel: SimpleCategorySelectViewModel = hiltViewModel(),
) {
	if (!enabled) return Box { TextField("...", {}, enabled = false) }

	val categoryListState = viewModel.categoryListState.collectAsState()
	val categoryList = categoryListState.value.categoryList ?: listOf()

	var isMenuOpen by remember { mutableStateOf(false) }
	var selectedCategoryName by remember { mutableStateOf(initialValue) }

	Box {
		TextField(
			value = selectedCategoryName,
			onValueChange = {},
			enabled = false,
			readOnly = true,
			label = { Text(stringResource(R.string.category_select_label)) },
			singleLine = true,
			trailingIcon = { DropdownArrowIcon(isMenuOpen) },
			modifier = Modifier.clickable { isMenuOpen = !isMenuOpen },
		)

		DropdownMenu(
			expanded = isMenuOpen,
			onDismissRequest = { isMenuOpen = false },
		) {
			categoryList.map {
				DropdownMenuItem(
					text = { Text(it.categoryName) },
					onClick = {
						selectedCategoryName = it.categoryName
						onValueSelect(it.id, it.categoryName)
						isMenuOpen = false
					}
				)
			}
		}
	}
}
