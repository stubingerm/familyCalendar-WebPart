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

        val lastInsertedQuery = dbch.getLastInsertedId()

        appointment.members.forEach{
            val isAdmin = if (it==userId) 1 else 0
            val memberQuery = "INSERT INTO APPOINTMENT_ATTENDEES (APPT_ID, USER_ID, ISADMIN)" +
                    "VALUES ($lastInsertedQuery, $it, $isAdmin)"
            dbch.update(memberQuery)
        }

    }

    fun deleteAppointment(userId: String, apptId: String) {

        val query = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = $apptId AND APPTOWNER_ID = $userId"

        dbch.update(query)

    }

    fun updateAppointment(userId: String, apptId: String, appointment: Appointment){

        val apptType = appointment.getType()

        val query = "UPDATE APPOINTMENTS " +
                "SET TITLE = '${appointment.title}'," +
                "LOCATION = '${appointment.location}'," +
                "TYPE = $apptType," +
                "REOCCURENCE = '${appointment.reoccurance}'," +
                "START = '${appointment.startDate.date}'," +
                "END = '${appointment.endDate.date}' " +
                "WHERE APPOINTMENT_ID=$apptId AND APPTOWNER_ID=$userId"

        dbch.update(query)

    }


}