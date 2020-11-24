package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.data.fpDateTime
import at.mstubinger.familyplanner.service.utils.DataBaseConnectionHelper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class LoginController {

    @PostMapping("/login")
    fun login(
            @RequestParam("mail") mail: String,
            @RequestParam("password") pwd: String
    ): LoggedInUser {

        val dbch = DataBaseConnectionHelper()

        val query = "SHOW DATABASES;"

        dbch.query(query)

        val userData = LoggedInUser(
                "-1",
                "John",
                "Doe",
                mail,
                arrayListOf(Appointment(
                        "",
                        "",
                        "",
                        "",
                        arrayListOf(""),
                        "",
                        fpDateTime(LocalDateTime.now()),
                        fpDateTime(LocalDateTime.now())
                ))
        )
        return userData
    }

}