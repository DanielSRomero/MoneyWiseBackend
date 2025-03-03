package domain.repository

import domain.models.UpdateUser
import domain.models.User

interface UserInterface {
    suspend fun getAllUser () : List <User>

    suspend fun getUserByName ( name : String) : List<User>

    suspend fun getUserByUserName (userName: String) : User?

    suspend fun postUser(user: User) : Boolean

    suspend fun updateUser(user: UpdateUser, userName: String) : Boolean

    suspend fun deleteUser(userName : String) : Boolean

    suspend fun login(userName: String, pass: String) : User?

    suspend fun register(user: UpdateUser) : User?
}