package data.persistence.repository

import data.persistence.models.user.UserDao
import data.persistence.models.user.UserTable
import data.persistence.models.suspendTransaction
import data.security.PasswordHash
import domain.mapper.toUser
import domain.models.user.UpdateUser
import domain.models.user.User
import domain.repository.UserInterface
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update


class PersistenceUserRepository : UserInterface {
    
    override suspend fun getAllUser(): List<User> {
        return suspendTransaction {
            UserDao.all().map { it.toUser() }
        }
    }

    /*
    getUserByName
    1.- Es exactamente igual que la anterior, solo que comparamos sobre el campo name de la tabla.
     */

    override suspend fun getUserByName(name: String): List<User> {
        return suspendTransaction {
            UserDao
                .find {
                    UserTable.name eq name
                }
                .map { it.toUser() }
        }
    }

    override suspend fun getUserByUserName(userName: String): User? {
        return suspendTransaction {
            UserDao
                .find {
                    UserTable.userName eq userName
                }
                .limit(1)
                .map {it.toUser()}
                .firstOrNull()
        }
    }



    override suspend fun updateUser(user: UpdateUser, userName: String): Boolean {
        var num = 0
        try {
            suspendTransaction {
                num = UserTable
                    .update({ UserTable.userName eq userName }) { stm ->
                        user.name?.let { stm[name] = it }
                        user.email?.let { stm[email] = it }
                        user.phone?.let { stm[phone] = it }
                        user.token?.let { stm[token] = it }
                    }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        return num == 1
    }


    override suspend fun deleteUser(userName: String): Boolean = suspendTransaction {
        val num = UserTable
            .deleteWhere{ UserTable.userName eq userName }
        num == 1
    }

    override suspend fun login(userName: String, pass: String): User? {
        val user = getUserByUserName(userName) ?: return null

        return try {
            val posibleHash = PasswordHash.hash(pass)
            if (posibleHash == user.password)
                user
            else
                null
        } catch (e: Exception) {
            println("Error en la autenticación: ${e.localizedMessage}")
            null
        }
    }

    override suspend fun register(user: UpdateUser): User? {

        return try {
            suspendTransaction {
                UserDao.new {
                    this.name = user.name!!
                    this.userName = user.userName!!
                    this.email = user.email!!
                    this.password = PasswordHash.hash(user.password!!)
                    this.phone = user.phone!!
                    this.token = user.token!!
                }
            }.let {
                it.toUser()
            }
        } catch (e: Exception) {
            println("Error en el registro de empleado: ${e.localizedMessage}")
            null
        }

    }


    suspend fun getUserByUserName1(userName: String): User? {
        return suspendTransaction {
            val resultRow = UserTable
                .select ( UserTable.userName eq userName )
                .singleOrNull()

            resultRow?.let {
                User(
                    userName = it[UserTable.userName],
                    password = it[UserTable.password],
                    name = it[UserTable.name],
                    email = it[UserTable.email],
                    phone = it[UserTable.phone],
                    token = it[UserTable.token],

                )
            }
        }
    }
}