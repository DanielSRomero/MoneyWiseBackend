package data.inmemory

import domain.models.User

object UserData {
    val listUser = mutableListOf<User>(
        User("JuanP", "pass1234", "Juan Pérez", "juan.perez@example.com", "5551234567", ""),
        User("MariaL", "mariaPass", "María López", "maria.lopez@example.com", "5559876543", ""),
        User("Carlitos", "carlosPass", "Carlos Gómez", "carlos.gomez@example.com", "5552345678", ""),
        User("AnaT", "ana123", "Ana Torres", "ana.torres@example.com", "5558765432", ""),
        User("Lascaras", "pedroPass", "Pedro Ramírez", "pedro.ramirez@example.com", "5553456789", ""),
        User("Luh", "luciaPass", "Lucía Méndez", "lucia.mendez@example.com", "5557654321", ""),
        User("Erjose", "josePass", "José Fernández", "jose.fernandez@example.com", "5556543210", ""),
        User("Ele", "elenaPass", "Elena Castillo", "elena.castillo@example.com", "5554321098", ""),
        User("Richard", "ricardoPass", "Ricardo Herrera", "ricardo.herrera@example.com", "5552109876", ""),
        User("La sofi", "sofiaPass", "Sofía Duarte", "sofia.duarte@example.com", "5558901234", "")
    )
}