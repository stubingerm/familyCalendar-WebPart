package at.mstubinger.familyplanner.service.utils

import at.mstubinger.familyplanner.service.data.Appointment
import java.sql.ResultSet

class DataBaseActionsHelper {

    private val dbch = DataBaseConnectionHelper()


    fun getAllAppointments (userId:String): ResultSet? {

        val appointments:ArrayList<Appointment> = ArrayList()

        //val query = "SELECT APPOINTMENT_ID, APPTOWNER_ID, TITLE, LOCATION, APPOINTMENT_TYPES.NAME as \"TYPE_\", REOCCURENCE, START, END FROM APPOINTMENTS, APPOINTMENT_TYPES WHERE APPOINTMENT_TYPES.APPT_TYPE_ID=APPOINTMENTS.TYPE AND " +
           //     "APPOINTMENTS.APPTOWNER_ID='$userId'"

        val query = "SELECT APPOINTMENT_ID, APPTOWNER_ID, MEMBERS, TITLE, LOCATION, APPOINTMENT_TYPES.NAME as \"TYPE_\", REOCCURENCE, START, END FROM APPOINTMENTS, APPOINTMENT_TYPES, (SELECT APPT_ID, GROUP_CONCAT(USER_ID) AS MEMBERS FROM `APPOINTMENT_ATTENDEES` GROUP BY APPT_ID) MEMBERSHIP WHERE APPOINTMENT_TYPES.APPT_TYPE_ID=APPOINTMENTS.TYPE AND APPOINTMENTS.APPTOWNER_ID='$userId' AND MEMBERSHIP.APPT_ID=APPOINTMENTS.APPOINTMENT_ID"

        println(query)

        return dbch.query(query)

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