package at.mstubinger.familyplanner.service.utils

import at.mstubinger.familyplanner.service.data.Appointment

class DataBaseActionsHelper {

    private val dbch = DataBaseConnectionHelper()


    fun getAllAppointments (userId:String){

        val appointments:ArrayList<Appointment> = ArrayList()

        val query = "SELECT * FROM"


    }


}