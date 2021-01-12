package at.mstubinger.familyplanner.service.controller

import at.mstubinger.familyplanner.service.data.Appointment
import at.mstubinger.familyplanner.service.utils.encryption.encryptionTestEnum
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class DecryptTestController {

    private val eh = encryptionTestEnum()
    private val encryption = eh.enc

    @PostMapping("/decryptTest")
    fun getAllAppointments(
            @RequestParam("cipher") cipher: String,
            response: HttpServletResponse,
            request: HttpServletRequest
    ): String? {

        lateinit var decrypt:String


        decrypt = encryption.decryptOrNull(cipher)
        return decrypt

    }

}