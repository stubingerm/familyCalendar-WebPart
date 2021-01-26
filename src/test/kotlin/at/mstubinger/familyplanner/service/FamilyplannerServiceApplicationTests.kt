package at.mstubinger.familyplanner.service

import at.mstubinger.familyplanner.service.utils.encryption.encryptionTestEnum
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FamilyplannerServiceApplicationTests {

    private val eh = encryptionTestEnum()
    private val encryption = eh.enc


    @Test
    fun contextLoads() {


    }


}
