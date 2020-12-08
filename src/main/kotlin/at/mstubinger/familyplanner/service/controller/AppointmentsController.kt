package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class AppointmentsController {


    @PostMapping("/appointments/update")
    fun login(
            @RequestParam("uid") userId: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ) {

        val appointments:ArrayList<Appointment> = ArrayList()

        val query = "SELECT * FROM APPOINTMENTS " +
                "WHERE APPTOWNER_ID='$userId'"

    }


}