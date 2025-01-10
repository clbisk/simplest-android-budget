package clbisk.simplestbudget.ui.reusable.util

import android.content.res.Resources
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.text.NumberFormat
import android.icu.util.Currency
import java.util.Locale

fun getDeviceLocale(): Locale = Resources.getSystem().configuration.locales[0]
fun getDeviceCurrencySymbol(): String =
	NumberFormat.getCurrencyInstance(getDeviceLocale()).currency?.symbol ?: "$"

fun formatCurrency(currencyValue: Float): String {
	return NumberFormatter.withLocale(getDeviceLocale())
		.unit(Currency.getInstance(getDeviceLocale()))
		.format(currencyValue)
		.toString()
}

fun maybeFormatCurrency(currencyValue: Float?): String? {
	if (currencyValue == null) return null
	return formatCurrency(currencyValue)
}

fun parseStringAsCurrencyFloat(str: String): Float? {
	val currencyFloat = str.toFloatOrNull() ?: return null

	return NumberFormatter.withLocale(getDeviceLocale())
		.precision(Precision.currency(Currency.CurrencyUsage.STANDARD))
		.format(currencyFloat).toBigDecimal().toFloat()
}
