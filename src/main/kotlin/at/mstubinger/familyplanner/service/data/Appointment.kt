package at.mstubinger.familyplanner.service.data

import java.time.Duration
import java.util.*

data class Appointment(

    //@Json(name = "appointmentId")
    val appointmentId: String,
    //@Json(name="title")
    val title: String,
    //@Json(name="location")
    val location: String,
    //@Json(name = "type")
    val type: String?,
    //@Json(name="members")
    val members: ArrayList<String>,
    //@Json(name = "reoccurance")
    val reoccurance: String,
    //@Json(name = "startDate")
    val startDate: fpDateTime,
    //@Json(name = "endDate")
    val endDate: fpDateTime

) {
    fun getDurationInMinutes(): Long {
        val duration = Duration.between(startDate.date,endDate.date).toMinutes()
        return duration
    }

    fun getDurationInHours(): Long {
        val duration = Duration.between(startDate.date,endDate.date).toHours()
        return duration
    }

    fun getDurationInMillis(): Long {
        val duration = Duration.between(startDate.date,endDate.date).toMillis()
        return duration
    }

    fun getType(): Int {

        when(this.type!!.toLowerCase()){
            "birthday" -> return 2
            "garbage" -> return 3
            else -> return 1
        }

    }
}