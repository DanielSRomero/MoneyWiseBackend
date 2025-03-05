package domain.usecase

import data.persistence.repository.PersistenceGoalsRepository
import data.persistence.repository.PersistenceUserRepository
import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.UpdateUser
import domain.models.user.User
import domain.usecase.goals.DeleteGoalUseCase
import domain.usecase.goals.GetAllGoalsUseCase
import domain.usecase.goals.GetGoalsFromUserUseCase
import domain.usecase.goals.InsertGoalUseCase
import domain.usecase.goals.UpdateGoalUseCase
import domain.usecase.user.DeleteUserUseCase
import domain.usecase.user.GetAllUsersUseCase
import domain.usecase.user.GetUserByUserNameUseCase
import domain.usecase.user.LoginUseCase
import domain.usecase.user.RegisterUseCase
import domain.usecase.user.UpdateUserUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {

    private val repositoryUser = PersistenceUserRepository()
    private val repositoryGoals = PersistenceGoalsRepository()

    val logger: Logger = LoggerFactory.getLogger("UserUseCaseLogger")

    private val getAllUsersUseCase = GetAllUsersUseCase(repositoryUser)
    private val getUserByUserNameUseCase = GetUserByUserNameUseCase(repositoryUser)
    private val updateUserUseCase = UpdateUserUseCase(repositoryUser)
    private val deleteUserUseCase = DeleteUserUseCase(repositoryUser)
    private val loginUseCase = LoginUseCase(repositoryUser)
    private val registerUseCase = RegisterUseCase(repositoryUser)

    private val deleteGoalUseCase = DeleteGoalUseCase(repositoryGoals)
    private val getAllGoalsUseCase = GetAllGoalsUseCase(repositoryGoals)
    private val getGoalsFromUserUseCase = GetGoalsFromUserUseCase(repositoryGoals)
    private val insertGoalUseCase = InsertGoalUseCase(repositoryGoals)
    private val updateGoalUseCase = UpdateGoalUseCase(repositoryGoals)



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

    suspend fun getAllGoals() = getAllGoalsUseCase()

    suspend fun getGoalsFromUser(userName : String) : List<Goal>? {
        if (userName.isNullOrBlank()){
            logger.warn("El nombre de usuario está vacío. No podemos buscar sus metas")
            return null
        }
        getGoalsFromUserUseCase.userName = userName
        val go = getGoalsFromUserUseCase()
        return if (go == null ) {
            logger.warn("No se ha encontrado metas para el usuario con el nombre $userName.")
            null
        }else{
            go
        }
    }



    suspend fun insertGoal(goal: Goal?, userName: String?) : Boolean{
        if (userName == null){
            logger.warn( "No existen datos del usuario a insertar")
            return false
        }
        if (goal == null){
            logger.warn( "No existen datos de la meta a insertar")
            return false
        }
        insertGoalUseCase.goal = goal
        insertGoalUseCase.user = getUserByUserName(userName)
        val res = insertGoalUseCase()
        return if (!res){
            logger.warn("No se ha insertado la meta. Posiblemente ya exista")
            false
        }else{
            true
        }
    }

    suspend fun updateGoal(updateGoal: UpdateGoal?, name: String, userName: String) : Boolean{
        if (updateGoal == null){
            logger.warn("No existen datos de la meta a actualizar")
            return false
        }

        updateGoalUseCase.goal = updateGoal
        updateGoalUseCase.name = name
        updateGoalUseCase.user = getUserByUserName(userName)

        return updateGoalUseCase()
    }


    suspend fun deleteGoal(name: String, userName : String) : Boolean{
        deleteGoalUseCase.name = name
        deleteGoalUseCase.user = getUserByUserName(userName)

        return deleteGoalUseCase()
    }
}