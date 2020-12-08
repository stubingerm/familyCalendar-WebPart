package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.utils.DataBaseActionsHelper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class AppointmentsController {

    private val dbah = DataBaseActionsHelper()

    @PostMapping("/appointments/getAll")
    fun getAllAppointments(
            @RequestParam("uid") userId: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ): ArrayList<Appointment> {

        return dbah.getAllAppointments(userId)

    }

    @PostMapping("appointments/create")

    fun createAppoinemtent(
            @RequestParam("uid") userId: String,
            
            response: HttpServletResponse,
            request: HttpServletRequest
    ): ArrayList<Appointment> {

        return dbah.getAllAppointments(userId)

    }

}