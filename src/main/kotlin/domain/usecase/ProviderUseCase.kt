package domain.usecase

import data.persistence.repository.PersistenceUserRepository
import domain.models.UpdateUser
import domain.models.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {

    private val repository = PersistenceUserRepository()
    val logger: Logger = LoggerFactory.getLogger("UserUseCaseLogger")

    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val getUserByUserNameUseCase = GetUserByUserNameUseCase(repository)
    private val updateUserUseCase = UpdateUserUseCase(repository)
    private val insertUserUseCase = InsertUserUseCase(repository)
    private val deleteUserUseCase = DeleteUserUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)
    private val registerUseCase = RegisterUseCase(repository)



    suspend fun getAllUsers() = getAllUsersUseCase()



    suspend fun getUserByUserName(userName : String) : User? {
        if (userName.isNullOrBlank()){
            logger.warn("El nombre de usuario está vacío. No podemos buscar el usuario")
            return null
        }
        getUserByUserNameUseCase.userName = userName
        val emp = getUserByUserNameUseCase()
        return if (emp == null) {
            logger.warn("No se ha encontrado un usuario con el nombre $userName.")
            null
        }else{
            emp
        }
    }



    suspend fun insertUser(user: User?) : Boolean{
        if (user == null){
            logger.warn( "No existen datos del usuario a insertar")
            return false
        }
        insertUserUseCase.user = user
        val res = insertUserUseCase()
        return if (!res){
            logger.warn("No se ha insertado el usuario. Posiblemente ya exista")
            false
        }else{
            true
        }
    }

    suspend fun updateUser(updateUser: UpdateUser?, userName: String) : Boolean{
        if (updateUser == null){
            logger.warn("No existen datos del usuario a actualizar")
            return false
        }

        updateUserUseCase.updateUser = updateUser
        updateUserUseCase.userName = userName
        return updateUserUseCase()
    }
    

    suspend fun deleteUser(userName : String) : Boolean{
        deleteUserUseCase.userName = userName
        return deleteUserUseCase()
    }

    suspend fun login(userName: String?, pass: String?) : User? = loginUseCase(userName, pass)

    suspend fun register(user : UpdateUser): User? {

        return if(
            user.userName.isNullOrBlank() ||
            user.password.isNullOrBlank()
        )
            null
        else
            registerUseCase(user)

    }
}