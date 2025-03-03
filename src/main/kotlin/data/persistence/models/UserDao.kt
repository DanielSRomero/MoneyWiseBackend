package data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDao(id : EntityID<Int>) :  IntEntity(id){
    companion object : IntEntityClass<UserDao>(UserTable)
    var name by UserTable.name
    var userName by UserTable.userName
    var email by UserTable.email
    var password by UserTable.password
    var phone by UserTable.phone
    var token by UserTable.token
}