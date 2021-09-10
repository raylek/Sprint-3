package ru.sber.oop

open class Room(val name: String, val size: Int) {

    val monster : Monster = Goblin("Physical", 1000, "Goblin", "Green one")
    fun Monster.getSalutation() = "Here I am"

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = monster.getSalutation()

    constructor (name: String) : this(name, 100) {

    }

}

open class TownSquare(): Room("Town Square", 1000) {

    final override fun load() = "Same as here.."

    override val dangerLevel = super.dangerLevel - 3

}