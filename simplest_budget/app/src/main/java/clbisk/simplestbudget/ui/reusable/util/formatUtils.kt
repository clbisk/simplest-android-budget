package clbisk.simplestbudget.ui.reusable.util

import android.content.res.Resources
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.util.Currency

fun formatCurrency(currencyValue: Long): String {
	val deviceLocale = Resources.getSystem().configuration.locales[0]
	return NumberFormatter.withLocale(deviceLocale)
		.unit(Currency.getInstance(deviceLocale))
		.format(currencyValue)
		.toString()
}

fun maybeFormatCurrency(currencyValue: Long?): String? {
	if (currencyValue == null) return null
	return formatCurrency(currencyValue)
}

fun parseStringAsInt(str: String): Int? {
	return str.toIntOrNull()
}

fun parseStringAsCurrencyLong(str: String): Long? {
	val deviceLocale = Resources.getSystem().configuration.locales[0]
	val currencyLong = str.toLongOrNull() ?: return null

	return NumberFormatter.withLocale(deviceLocale)
		.precision(Precision.currency(Currency.CurrencyUsage.STANDARD))
		.format(currencyLong).toBigDecimal().toLong()
}
