package at.mstubinger.familyplanner.service.utils

import at.mstubinger.familyplanner.service.data.Appointment
import java.time.format.DateTimeFormatter

class DataBaseActionsHelper {

    private val dbch = DataBaseConnectionHelper()


    fun getAllAppointments (userId:String): ArrayList<Appointment> {

        val appointments:ArrayList<Appointment> = ArrayList()

        val query = "SELECT * FROM APPOINTMENTS" +
                "WHERE APPTOWNER_ID='$userId'"


        return appointments

    }

    fun createAppointment(userId: String, appointment:Appointment){

        var apptType=1

        when(appointment.type!!.toLowerCase()){
            "garbage" ->  apptType = 3
            "birthday" -> apptType = 2
            else -> apptType = 1
        }

        val isFullDayAppt = 0

        val startDate = appointment.startDate.date.toString()
        val endDate = appointment.endDate.date.toString()

        val query = "INSERT INTO APPOINTMENTS (APPTOWNER_ID, TITLE, LOCATION, TYPE, REOCCURENCE, START, END, ISFULLDAYAPPT)" +
                "VALUES ($userId, '${appointment.title}', '${appointment.location}',$apptType , '${appointment.reoccurance}', '$startDate', '$endDate', $isFullDayAppt)"

        dbch.update(query)

    }


}