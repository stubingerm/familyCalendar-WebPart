package at.mstubinger.familyplanner.service.utils.encryption

import se.simbio.encryption.Encryption
import third.part.android.util.Base64

class encryptionTestEnum() {
    private val key = "04cea64fffccb6c34adc70af6cec7563"
    private val salt = "5b82af8fda1833d8c5fac08958d29802"
    private val iv = ByteArray(16)
    //val enc = Encryption.getDefault(key, salt, iv)
    val enc = Encryption.Builder.getDefaultBuilder(key, salt, iv)
            .setBase64Mode(Base64.DEFAULT)
            .build()

    fun encrypt(s:String):String{
        var cipher = enc.encrypt(s)
        val re = Regex("[^ -~]+")
        cipher = re.replace(cipher, "")

        return cipher
    }

}