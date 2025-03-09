package data.persistence.models.goals

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.TextColumnType

object GoalsTable: IntIdTable("Goals") {
    val userUserName = varchar("userUserName", 100)
    val name = varchar("name", 100).uniqueIndex()
    val money = integer("money")
    val year = integer("year")
    val image = registerColumn<String>("image", TextColumnType("LONGTEXT")).nullable()
}