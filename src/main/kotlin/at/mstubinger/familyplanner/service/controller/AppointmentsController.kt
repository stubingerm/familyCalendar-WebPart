package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.data.fpDateTime
import at.mstubinger.familyplanner.service.utils.DataBaseActionsHelper
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
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
            @RequestParam("title") title: String,
            @RequestParam("location") location: String,
            @RequestParam("type") type: String,
            @RequestParam("members") members: ArrayList<String>,
            @RequestParam("reoccurance") reoccurance: String,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime,
            @RequestParam("isFullDayAppointment", required = false) isFullDayAppointment: Boolean,
            response: HttpServletResponse,
            request: HttpServletRequest
    ){

        val appointment = Appointment("",title,location,type,members,reoccurance, fpDateTime(startDate), fpDateTime(endDate))

        dbah.createAppointment(userId,appointment)

    }

    @PostMapping("appointments/update")

    fun updateAppointment(
            @RequestParam("uid") userId: String,
            @RequestParam("apptId") apptId: String,
            @RequestParam("title") title: String,
            @RequestParam("location") location: String,
            @RequestParam("type") type: String,
            @RequestParam("members") members: ArrayList<String>,
            @RequestParam("reoccurance") reoccurance: String,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime,
            @RequestParam("isFullDayAppointment", required = false) isFullDayAppointment: Boolean,
            response: HttpServletResponse,
            request: HttpServletRequest
    ){

        val appointment = Appointment("",title,location,type,members,reoccurance, fpDateTime(startDate), fpDateTime(endDate))

        dbah.updateAppointment(userId,apptId,appointment)

    }

    @PostMapping("appointments/cancel")
    fun cancelAppointment(
            @RequestParam("uid") userId: String,
            @RequestParam("apptId") apptId: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ){

        dbah.deleteAppointment(userId, apptId)

    }

}