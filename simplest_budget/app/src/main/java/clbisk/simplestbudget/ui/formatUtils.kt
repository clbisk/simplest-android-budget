package clbisk.simplestbudget.ui

import android.content.res.Resources
import android.icu.number.NumberFormatter
import android.icu.util.Currency

fun formatCurrency(value: Int, hundredthsValue: Int? = 0): String {
	val fractionalCurrency = hundredthsValue?.toFloat()?.div(100) ?: 0.toFloat()
	val currencyValue = value + fractionalCurrency

	val deviceLocale = Resources.getSystem().configuration.locales[0]
	return NumberFormatter.withLocale(deviceLocale)
		.unit(Currency.getInstance(deviceLocale))
		.format(currencyValue)
		.toString()
}

fun parseStringAsInt(str: String): Int? {
	return str.toIntOrNull()
}
