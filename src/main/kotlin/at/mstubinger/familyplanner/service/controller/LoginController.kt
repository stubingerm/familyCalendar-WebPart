package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.data.fpDateTime
import at.mstubinger.familyplanner.service.exceptions.UserNotFoundException
import at.mstubinger.familyplanner.service.utils.DataBaseConnectionHelper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class LoginController {

    @Throws(UserNotFoundException::class)
    @PostMapping("/login")
    fun login(
            @RequestParam("mail") mail: String,
            @RequestParam("password") password: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ): LoggedInUser? {

        val dbch = DataBaseConnectionHelper()

        val query = "SELECT * FROM USERS WHERE EMAIL = '$mail' " /*+
                "AND PASSWORD = '$password';"*/

        println("SQL QUERY: $query")

        val resultSet = dbch.query(query)

        var rows = 0

        if (resultSet!!.next()){
            rows++
            println(resultSet.getString("EMAIL"))
        }

        if (rows<1){
            response.status=401
            // mail/pwd combination not found
        } else if(rows==1){
            val userData = LoggedInUser(
                    resultSet.getString("ID"),
                    resultSet.getString("FIRSTNAME"),
                    resultSet.getString("LASTNAME"),
                    resultSet.getString("EMAIL"),
                    ArrayList<Appointment>()
            )
            response.status=200
            return userData
        } else {
            response.status=401
        }

        return null


    }

}