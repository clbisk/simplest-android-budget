package clbisk.simplest_budget.data.transactionRecord

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimestampConverters {
	@TypeConverter
	fun timestampFromUnixMilliseconds(ms: Int?): Timestamp? {
		return ms?.let { Timestamp(it.toLong()) }
	}

	@TypeConverter
	fun timestampToUnixMilliseconds(timestamp: Timestamp?): Int? {
		return timestamp?.time?.toInt()
	}
}