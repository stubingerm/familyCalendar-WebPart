package at.mstubinger.familyplanner.service.utils

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.fpDateTime
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataBaseActionsHelper {

    private val dbch = DataBaseConnectionHelper()

    private fun getQueryForAllAppointments(userId: String):String{
        val query = "SELECT APPOINTMENT_ID, APPTOWNER_ID, MEMBERS, TITLE, LOCATION, APPOINTMENT_TYPES.NAME as \"TYPE_\", REOCCURENCE, START, END FROM APPOINTMENTS, APPOINTMENT_TYPES, (SELECT APPT_ID, GROUP_CONCAT(USER_ID) AS MEMBERS FROM `APPOINTMENT_ATTENDEES` GROUP BY APPT_ID) MEMBERSHIP WHERE APPOINTMENT_TYPES.APPT_TYPE_ID=APPOINTMENTS.TYPE AND APPOINTMENTS.APPTOWNER_ID='$userId' AND MEMBERSHIP.APPT_ID=APPOINTMENTS.APPOINTMENT_ID"
        println(query)
        return query
    }

    fun getAllAppointments (userId:String): ResultSet? {

        //val query = "SELECT APPOINTMENT_ID, APPTOWNER_ID, TITLE, LOCATION, APPOINTMENT_TYPES.NAME as \"TYPE_\", REOCCURENCE, START, END FROM APPOINTMENTS, APPOINTMENT_TYPES WHERE APPOINTMENT_TYPES.APPT_TYPE_ID=APPOINTMENTS.TYPE AND " +
           //     "APPOINTMENTS.APPTOWNER_ID='$userId'"

        val query = getQueryForAllAppointments(userId)

        return dbch.query(query)

    }

    fun getAllAppointmentsAsList (userId: String): ArrayList<Appointment> {

        //val query = "SELECT APPOINTMENT_ID, APPTOWNER_ID, MEMBERS, TITLE, LOCATION, APPOINTMENT_TYPES.NAME as " +
                "\"TYPE_\", REOCCURENCE, START, END FROM APPOINTMENTS, APPOINTMENT_TYPES, (SELECT APPT_ID, GROUP_CONCAT(USER_ID) AS MEMBERS FROM `APPOINTMENT_ATTENDEES` GROUP BY APPT_ID) MEMBERSHIP WHERE APPOINTMENT_TYPES.APPT_TYPE_ID=APPOINTMENTS.TYPE AND APPOINTMENTS.APPTOWNER_ID='$userId' AND MEMBERSHIP.APPT_ID=APPOINTMENTS.APPOINTMENT_ID"

        val query = getQueryForAllAppointments(userId)
        lateinit var resultSet: ResultSet
        val appointments = ArrayList<Appointment>()

        resultSet = dbch.query(query)!!


        while (resultSet.next()){

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


            val appointment = Appointment(
                    resultSet.getString("APPOINTMENT_ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("LOCATION"),
                    resultSet.getString("TYPE_"),
                    ArrayList(resultSet.getString("MEMBERS").split(",").map {it.trim()}),
                    resultSet.getString("REOCCURENCE"),
                    fpDateTime(LocalDateTime.parse(resultSet.getString("START"),formatter)),
                    fpDateTime(LocalDateTime.parse(resultSet.getString("END"),formatter))
            )

            appointments.add(appointment)

        }

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