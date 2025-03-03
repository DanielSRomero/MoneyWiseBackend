package data.security

import domain.security.PasswordHashInterface
import java.security.MessageDigest

object PasswordHash : PasswordHashInterface {

    override fun hash(pass: String): String {
        val passArr = pass.toByteArray() //convierto la password a array
        val messageDigest = MessageDigest.getInstance("SHA-256") //Objeto que me hasheará
        val hashByte : ByteArray = messageDigest.digest(passArr) //Ya tengo el hash en un array de bytes.
        val hashHex = hashByte.joinToString("") { "%02x".format(it) }
        return hashHex
    }

    override fun verify(pass: String, passHash: String): Boolean {
        return hash(pass) == passHash
    }


}