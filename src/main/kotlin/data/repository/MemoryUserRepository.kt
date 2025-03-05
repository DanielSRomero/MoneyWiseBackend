package data.repository

import data.inmemory.UserData
import domain.models.user.UpdateUser
import domain.models.user.User
import domain.repository.UserInterface

class MemoryUserRepository : UserInterface {
    override suspend fun getAllUser(): List<User> {
        return UserData.listUser
    }

    override suspend fun getUserByName(name: String): List<User> {
        return UserData.listUser.filter { it.name == name }
    }

    override suspend fun getUserByUserName(userName: String): User? {
        return UserData.listUser.filter { it.userName == userName}.firstOrNull()
    }

    override suspend fun postUser(user: User): Boolean {
        val emp = getUserByUserName(user.userName)
        return if (emp!= null) {
            false
        } else{
            UserData.listUser.add(user)
            true
        }
    }

    override suspend fun updateUser(updateUser: UpdateUser, userName: String): Boolean {
        val index = UserData.listUser.indexOfFirst { it.userName == userName }
        return if (index != -1) {
            val originUser = UserData.listUser[index]
            UserData.listUser[index] =  originUser
                .copy(
                    name = updateUser.name ?: originUser.name,
                    userName = updateUser.userName ?: originUser.userName,
                    email = updateUser.email ?: originUser.email,
                    phone = updateUser.phone ?: originUser.phone,
                    token = updateUser.token ?: originUser.token,
                    )
            true
        }
        else{
            false
        }
    }

    override suspend fun deleteUser(userName: String): Boolean {
        val index = UserData.listUser.indexOfFirst { it.userName == userName }
        return if (index != -1) {
            UserData.listUser.removeAt(index)
            true
        }else{
            false
        }
    }

    override suspend fun login(userName: String, pass: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun register(user: UpdateUser): User? {
        TODO("Not yet implemented")
    }

}