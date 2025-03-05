package data.persistence.models.goals

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GoalDao(id : EntityID<Int>) :  IntEntity(id) {
    companion object : IntEntityClass<GoalDao>(GoalsTable)
    var userUserName by GoalsTable.userUserName
    var name by GoalsTable.name
    var money by GoalsTable.money
    var year by GoalsTable.year
    var image by GoalsTable.image
}