package clbisk.simplestbudget.ui

import java.text.NumberFormat

fun formatCurrency(value: Int): String {
	return NumberFormat.getCurrencyInstance().format(value)
}
