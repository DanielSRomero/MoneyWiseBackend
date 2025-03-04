package ktor

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases(){
    val driver = "org.mariadb.jdbc.Driver"
    val url = "jdbc:mariadb://localhost:3306/dbMoneyWise"
    val username = "dani"
    val password = "dani"

    try {
        Database.connect(
            url = url,
            driver = driver,
            user = username,
            password = password
        )
        log.info ("He establecido bien la conexión")
    }catch (e: Exception){
        log.error("Database connection failed: ${e.message}")
    }


}