package me.backword.pluginapi.commands.arguments

import me.backword.pluginapi.commands.exception.CommandException
import org.bukkit.Bukkit

fun interface ArgumentResolver<T> {
    companion object {
        private val COMMAND_EXCEPTION = CommandException()
        val BOOLEAN = of(String::toBoolean)
        val SHORT = of(String::toShort)
        val INT = of(String::toInt)
        val LONG = of(String::toLong)
        val FLOAT = of(String::toFloat)
        val DOUBLE = of(String::toDouble)
        val PLAYER = of(Bukkit::getPlayer)
        fun ofShortRange(min: Short = Short.MIN_VALUE, max: Short = Short.MAX_VALUE) = ofRange(min, max, String::toShort)
        fun ofIntRange(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE) = ofRange(min, max, String::toInt)
        fun ofLongRange(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE) = ofRange(min, max, String::toLong)
        fun ofFloatRange(min: Float = Float.MIN_VALUE, max: Float = Float.MAX_VALUE) = ofRange(min, max, String::toFloat)
        fun ofDoubleRange(min: Double = Double.MIN_VALUE, max: Double = Double.MAX_VALUE) = ofRange(min, max, String::toDouble)

        fun <T> of(resolver: (String) -> T) = ArgumentResolver(resolver)
        private fun <T> ofRange(min: T, max: T, mapper: (String) -> T): ArgumentResolver<T> where T : Comparable<T>, T : Number {
            return of {
                val number = mapper(it)
                if (number < min || number > max) throw COMMAND_EXCEPTION
                number
            }
        }
    }

    fun resolve(arg: String): T
}