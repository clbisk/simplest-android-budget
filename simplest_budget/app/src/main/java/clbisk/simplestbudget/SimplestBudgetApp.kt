package clbisk.simplestbudget

import android.app.Application
import androidx.compose.runtime.Composable
import clbisk.simplestbudget.ui.nav.AppNavGraph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimplestBudgetApp : Application() {}

@Composable
fun SimplestBudgetUI() {
	AppNavGraph()
}
