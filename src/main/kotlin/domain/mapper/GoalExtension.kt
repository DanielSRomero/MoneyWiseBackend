package domain.mapper

import data.persistence.models.goals.GoalDao
import domain.models.goals.Goal
import domain.models.goals.UpdateGoal

fun Goal.toUpdateGoal() : UpdateGoal {
    return UpdateGoal(
        userUserName = userUserName,
        name = name,
        money = money,
        year = year,
        image = image
    )
}


fun UpdateGoal.toGoal() : Goal {
    return Goal(
        userUserName = userUserName!!,
        name = name!!,
        money = money!!,
        year = year!!,
        image = image
    )
}

fun GoalDao.toGoal () : Goal {

    val g = Goal(
        this.userUserName,
        this.name,
        this.money,
        this.year,
        this.image ?: "",
    )
    return g
}