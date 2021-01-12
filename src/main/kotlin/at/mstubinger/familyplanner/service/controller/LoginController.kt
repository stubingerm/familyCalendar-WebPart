package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.data.LoggedInUser
import at.mstubinger.familyplanner.service.exceptions.UserNotFoundException
import at.mstubinger.familyplanner.service.utils.DataBaseConnectionHelper
import at.mstubinger.familyplanner.service.utils.encryption.encryptionTestEnum
import com.google.gson.Gson
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import se.simbio.encryption.Encryption
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList
import third.part.android.util.Base64
import java.security.NoSuchAlgorithmException

@RestController
class LoginController {

    private val eh = encryptionTestEnum()
    private val encryption = eh.enc

    @Throws(UserNotFoundException::class)
    @PostMapping("/login")
    fun login(
            @RequestParam("mail") mail: String,
            @RequestParam("password") password: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ): String? {

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

            val gson = Gson()

            val jsonString = gson.toJson(userData)


            //val encryptedString = encryption.encryptOrNull(jsonString)
            val encryptedString = eh.encrypt(jsonString)

            val decryptedString = encryption.decryptOrNull(encryptedString)

            //return jsonString
            return encryptedString


        } else {
            response.status=401
        }

        return ""


    }

}