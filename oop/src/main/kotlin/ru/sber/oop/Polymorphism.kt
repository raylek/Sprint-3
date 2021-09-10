package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt()

    fun attack(opponent: Fightable): Int


}


class Player (override val powerType: String, override var healthPoints: Int, val name: String, var isBlessed: Boolean) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val damage = damageRoll
        if (isBlessed) {
            opponent.healthPoints -= damage * 2
            return damage * 2
        } else {
            opponent.healthPoints -= damage
            return damage
        }
    }

}

abstract class Monster (override val powerType: String, override var healthPoints: Int, val name: String, val description: String) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val damage = damageRoll
        opponent.healthPoints -= damage
        return damage
    }
}

class Goblin(powerType: String, healthPoints: Int, name: String, description: String) : Monster (powerType, healthPoints, name, description) {

    override val damageRoll: Int
        get() = Random.nextInt() / 2

}


