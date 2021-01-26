package at.mstubinger.familyplanner.service.utils.encryption


    private val eh = encryptionTestEnum()
    private val encryption = eh.enc

    fun main() {

        println("STARTING DECRYPT TEST...")

        val cipher = "AXqUP6FyotKWuuHCOkltuo2jgLvQosif3tfk3X5/0h+jht1NnTCV4Te24WTJgqSngLjz/JTpaNmlpuyQvaU47NWJReHQ5nesp0FNz3D5HmN2y+B50fRRzdIpmL20PwNYohzRKetiSJSORvzd6S4/lKg6ZMV+p2haCBxBQcJujbAuCTAQYIC+ZCO7S8BvYVoel5mZxeekNiwvqwiX79QKqjbZrGmwU8QC6GRqH0gWRi9/clORbnm/95ZoiedN7Pxt0PtCCUOa+Un1Q3GEbmu8/SfFNiL1wBTlovtl9HjuQ0W9QDuCMBXb/wZzuWP1UhjWuAQbH4cNsQdb5UtYgZtLZhj75znhbVArBUl7iSdgdRQd90Faroj50/+YXuU69ahuQwfJs+msBwrGGJjPLIKsOBtaRJOIxgI8xOB0Fp7oeIJH/NZGZpKllFfnkW/HOvlP2IGYf+x4Dta4C2mtW1Q8Xw=="
        println("INPUT CIPHER: $cipher")

        val text = encryption.decryptOrNull(cipher)

        println("Decryption output: $text")


}