package data.inmemory

import domain.models.User

object UserData {
    val listUser = mutableListOf<User>(
        User("Juan Pérez", "JuanP",  "juan.perez@example.com", "pass1234", "5551234567", ""),
        User("María López", "MariaL","maria.lopez@example.com", "mariaPass", "5559876543", ""),
        User("Carlos Gómez", "Carlitos","carlos.gomez@example.com", "carlosPass", "5552345678", ""),
        User("Ana Torres", "AnaT","ana.torres@example.com", "ana123", "5558765432", ""),
        User("Pedro Ramírez", "Lascaras","pedro.ramirez@example.com", "pedroPass", "5553456789", ""),
        User("Lucía Méndez", "Luh","lucia.mendez@example.com", "luciaPass", "5557654321", ""),
        User("José Fernández", "Erjose","jose.fernandez@example.com", "josePass", "5556543210", ""),
        User("Elena Castillo", "Ele","elena.castillo@example.com", "elenaPass", "5554321098", ""),
        User("Ricardo Herrera", "Richard","ricardo.herrera@example.com", "ricardoPass", "5552109876", ""),
        User("Sofía Duarte", "La sofi","sofia.duarte@example.com", "sofiaPass", "5558901234", "")
    )
}