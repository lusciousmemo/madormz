package kenji.pilotprojects.calculatorapp.util

import java.security.MessageDigest
import kotlin.experimental.and

//Example
//val password = "myPassword"
//val salt = "mySalt" // ควรเป็นค่าที่ไม่ซ้ำกันสำหรับแต่ละผู้ใช้
//val hashedPassword = password.toSha256(salt)

fun hashPassword(password: String, salt: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    md.update(salt.toByteArray())
    val bytes = md.digest(password.toByteArray())
    val sb = StringBuilder()
    for (i in bytes.indices) {
        sb.append(Integer.toString((bytes[i] and 0xff.toByte()) + 0x100, 16).substring(1))
    }
    return sb.toString()
}

fun String.toSha256(salt: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val saltedPassword = this + salt
    val hash = digest.digest(saltedPassword.toByteArray())
    val hexString = StringBuffer()

    for (i in hash) {
        val hex = Integer.toHexString(0xff and i.toInt())
        if (hex.length == 1) hexString.append('0')
        hexString.append(hex)
    }

    return hexString.toString()
}