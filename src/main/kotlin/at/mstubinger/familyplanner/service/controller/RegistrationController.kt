package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.exceptions.UserNotFoundException
import at.mstubinger.familyplanner.service.utils.DataBaseConnectionHelper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLIntegrityConstraintViolationException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class RegistrationController {



    @Throws(UserNotFoundException::class)
    @PostMapping("/register")
    fun login(
            @RequestParam("firstname") firstname: String,
            @RequestParam("lastname") lastname: String,
            @RequestParam("mail") mail: String,
            @RequestParam("password") password: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ) {

        val dbch = DataBaseConnectionHelper()

        var activationString = "1234567890abcdef"

        val uniqueCheckQuery = "SELECT COUNT(EMAIL) FROM USERS WHERE EMAIL='$mail'"

        val uniqueCheckResultSet = dbch.query(uniqueCheckQuery)



        val query = "INSERT INTO USERS" +
                "(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, ISACTIVATED, ACTIVATIONSTRING)" +
                "VALUES ('$mail', '$firstname', '$lastname', '$password', 0, '$activationString')"

        println("SQL QUERY: $query")

        dbch.update(query)

    }

}