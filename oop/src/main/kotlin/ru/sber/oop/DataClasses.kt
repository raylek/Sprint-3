package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if (other !is User || other == null) return false
        return this.city == other.city && this.name == other.name && this.age == other.age
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "Stephen")
    user1.city = "Tomsk"
    val user3 = user1.copy()
    user3.city = "Omsk"


    print(user1.equals(user3))
}