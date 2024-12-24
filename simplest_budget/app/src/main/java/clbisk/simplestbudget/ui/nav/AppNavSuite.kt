package clbisk.simplestbudget.ui.nav

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppNavSuite(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit,
) {
	val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
		currentWindowAdaptiveInfo(),
	)

	NavigationSuiteScaffold(
		modifier = modifier,
		layoutType = layoutType,
		navigationSuiteItems = {
			TopLevelDestination.entries
		}
	) {
		content()
	}
}
