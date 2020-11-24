package at.mstubinger.familyplanner.service.data


/**
 * Data class that captures user information for logged in users
 */
data class LoggedInUser(
    //@Json(name="uid")
        val uid: String,
    //@Json(name = "firstName")
        val firstname: String,
    //@Json(name = "lastName")
        val lastname: String,
    //@Json(name = "email")
        val email: String,
    //@Json(name = "appointments")
        val appointments: ArrayList<Appointment>?

)