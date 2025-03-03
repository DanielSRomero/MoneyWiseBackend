package data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object  UserTable: IntIdTable("User") {
    val userName = varchar("userName", 20).uniqueIndex()
    val name = varchar("name", 100)
    val email = varchar("email", 100)
    val password = varchar("password", 255)
    val phone = varchar("phone", 20)
    val token = varchar("token", 255).nullable()

}