package data.persistence.repository

import data.persistence.models.goals.GoalDao
import data.persistence.models.goals.GoalsTable
import data.persistence.models.suspendTransaction
import domain.mapper.toGoal
import domain.models.goals.Goal
import domain.models.goals.UpdateGoal
import domain.models.user.User
import domain.repository.GoalsInterface
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and


class PersistenceGoalsRepository : GoalsInterface {
    override suspend fun getAllGoals(): List<Goal> {
        return suspendTransaction {
            GoalDao.all().map { it.toGoal() }
        }
    }

    override suspend fun getGoalByName(name: String, user: User): Goal? {
        return suspendTransaction {
            GoalDao
                .find {
                    (GoalsTable.userUserName eq user.userName) and (GoalsTable.name eq name)
                }
                .map { it.toGoal() }
                .firstOrNull()
        }
    }


    override suspend fun getGoalsByUserUserName(userUserName: String): List<Goal> {
        return suspendTransaction {
            GoalDao
                .find {
                    GoalsTable.userUserName eq userUserName
                }
                .map { it.toGoal() }
        }
    }


    override suspend fun postGoal(goal: Goal, user: User): Goal? {
        val g = getGoalByName(goal.name, user)
        return if (g == null) {
            suspendTransaction {
                GoalDao.new {
                    this.userUserName = goal.userUserName
                    this.name = goal.name
                    this.money = goal.money
                    this.year = goal.year
                    this.image = goal.image
                }
            }.toGoal()

        } else
            null
    }

    override suspend fun updateGoal(goal: UpdateGoal, name: String, user: User): Boolean {
        var num = 0
        try {
            suspendTransaction {
                num = GoalsTable
                    .update({
                        (GoalsTable.userUserName eq user.userName) and (GoalsTable.name eq name)
                    }) { stm ->
                        goal.userUserName?.let { stm[userUserName] = it }
                        goal.name?.let { stm[GoalsTable.name] = it }
                        goal.money?.let { stm[money] = it }
                        goal.year?.let { stm[year] = it }
                        goal.image?.let { stm[image] = it }
                    }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        return num == 1    }

    override suspend fun deleteGoal(name: String, user: User): Boolean = suspendTransaction {
        val num = GoalsTable
            .deleteWhere{
                (GoalsTable.userUserName eq user.userName) and (GoalsTable.name eq name)
            }
        num == 1
    }

}