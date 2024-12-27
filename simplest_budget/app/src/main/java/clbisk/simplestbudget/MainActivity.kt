package clbisk.simplestbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.glance.appwidget.updateAll
import clbisk.simplestbudget.ui.nav.AppNavGraph
import clbisk.simplestbudget.ui.theme.SimplestBudgetTheme
import clbisk.simplestbudget.widget.SimplestBudgetWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		runBlocking { SimplestBudgetWidget().updateAll(this@MainActivity) }
		setContent {
			SimplestBudgetTheme {
				Surface(
					modifier = Modifier.fillMaxSize()
				) {
					AppNavGraph()
				}
			}
		}
	}
}
