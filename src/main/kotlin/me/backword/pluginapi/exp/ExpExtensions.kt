package me.backword.pluginapi.exp

import org.bukkit.entity.Player
import kotlin.math.pow
import kotlin.math.roundToInt

fun getExpToLevelUp(level: Int) = when {
    level <= 15 -> 2 * level + 7
    level <= 30 -> 5 * level - 38
    else -> 9 * level - 158
}

fun getExpAtLevel(level: Int) = when {
    level <= 16 -> (level.toDouble().pow(2.0) + 6 * level).toInt()
    level <= 31 -> (2.5 * level.toDouble().pow(2.0) - 40.5 * level + 360.0).toInt()
    else -> (4.5 * level.toDouble().pow(2.0) - 162.5 * level + 2220.0).toInt()
}

fun Player.getExperience() = getExpAtLevel(level) + (getExpToLevelUp(level) * exp).roundToInt()

fun Player.setExperience(exp: Int) {
    val currentExp = getExperience()
    this.exp = 0F
    level = 0
    giveExp(currentExp + exp)
}