package clbisk.simplestbudget.ui.reusable.budgetcategories.categoryselect

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import clbisk.simplestbudget.R

@Composable
private fun RightArrowIcon() = Icon(
	Icons.AutoMirrored.Default.KeyboardArrowRight,
	stringResource(R.string.arrow_right_icon)
)

@Composable
private fun DownArrowIcon() = Icon(
	Icons.Default.ArrowDropDown,
	stringResource(R.string.arrow_down_icon)
)

@Composable
fun DropdownArrowIcon(isDropdownOpen: Boolean) {
	if (isDropdownOpen) DownArrowIcon() else RightArrowIcon()
}
