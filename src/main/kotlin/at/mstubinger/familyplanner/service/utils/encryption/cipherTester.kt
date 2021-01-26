package at.mstubinger.familyplanner.service.utils.encryption


    private val eh = encryptionTestEnum()
    private val encryption = eh.enc

    fun main() {

        println("STARTING DECRYPT TEST...")

        val cipher = "o4ivk8tMWIU9Hgn/2s1/9005hWu9citkxggQkezClN02HoyQnVB/Sd54hHFgnzKiK76Rtu5S0Eq9gmGE4FEsNpwz8LAPC+CIjTRsAp6MUJl/9XzgSXKAUjLFO96KyhrSqGf232Jn9ZGKkXgJG8xfg2kChzGHBtZPh2uT6PUV43YiFzbFPA84X34zGxBRIERD5D4l4ycjnl5Htgl7Twdclpu6bzor6qNitHvhE5XzDrG+GbKaTxf8u6heW+I+/7eDwbYhKDOPn2Hy0xpdwwFvCYG93T9P8KtsJUnx41oyFJDow6bVNHcbWfgWdyh7vbYMhN1/su8DJlOMRFLGge1VvKXqfy+/0aR49/jkpjoiB8rEoSeaPCJ2adGuyx4gFz7pAGVptT4+9tRoJ4ogAzP0XzxR73IlAOZOG9oP0paa8larCXWcPHZwvxTH6zmdCYJCBU6uN2W52KgE8oRUqJ5N3YyNgio2ek9+BBhrGfljGvHdDjDBXsYYLYev6HZEVsjBGE++jPyb5zVZSmWrCX4SkZYj8n91cs6jfQQ5BmxTEqS6UvVydi8mH1z3j7gpqlUlpqPmqs1h/A6cKRxhfVvgzq15pIS+a3XniC8pPFVRjfuyoB+tC36nJUG5BGe17GS/SgHQTRelvI8xkHld7ANG1eWcf8M5ISmnoALS0Iz07SES2M7o6NIACohosfPcXrUM9QYcWkRafzkHPKMwUJBZquplARNSRqxxnGGzLDtLbSBvgEYjS5i9dm34rZAx3ugQb5TWKH+eLIMIogHnlnkV9kjDss2Eb2z91kr2/3yWAxnzxdHf7f8caP/r1XZVcVNly7ToPBwPIblLY8fYxLegvNPiuF8grzjRQvzEfuFUJYdHPZcJMegaQDR0oL8uh2JzzfSCE8J5P+sIzhCXKYtdKTUJDijkDKmMqVeAH9ARwIoYmYrYbiRtqvQGrvGsmqIjVlV62hm3b9uXGLUppvmbA6Q71qvwKWY5zMkOMth5eKxldtpmt9JnhvmOXroOWZxaQe3QEDLKPlAe3Ifa19BZz71G0OZ9jWY6lylWM3H7qRs="
        println("INPUT CIPHER: $cipher")

        val text = encryption.decryptOrNull(cipher)

        println("Decryption output: $text")


}