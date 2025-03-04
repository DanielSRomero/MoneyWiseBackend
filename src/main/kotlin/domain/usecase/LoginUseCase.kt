package domain.usecase

import domain.mapper.toUpdateUser
import domain.mapper.toUser
import domain.models.User
import domain.repository.UserInterface
import domain.security.JwtConfig

class LoginUseCase (val repository : UserInterface){
    suspend operator fun invoke(userName: String ?, pass:String ?): User ? {
        if (userName.isNullOrBlank() || pass.isNullOrBlank()) return null

        return try{
            val em = repository.login(userName, pass) ?: null
            println(em.toString())
            em!!.token = JwtConfig.generateToken(em.userName)
            val updateUser = em.toUpdateUser()
            val res = repository.updateUser(updateUser, userName)
            return if (res)
                updateUser.toUser()
            else
                null
        }catch (e: Exception){
            println("Error en login:  ${e.localizedMessage}")
            null
        }
    }
}