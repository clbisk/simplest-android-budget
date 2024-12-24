package clbisk.simplestbudget.widget.config

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import clbisk.simplestbudget.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetConfigAppBar(
	modifier: Modifier = Modifier,
) {
	TopAppBar(
		title = { Text(LocalContext.current.getString(R.string.widget_config_title)) },
		modifier = modifier,
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = Color.Transparent,
		)
	)
}
