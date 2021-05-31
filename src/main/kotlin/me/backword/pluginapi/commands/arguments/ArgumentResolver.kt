package me.backword.pluginapi.commands.arguments

import me.backword.pluginapi.commands.exception.InvalidSyntaxException
import org.bukkit.Bukkit

fun interface ArgumentResolver<T> {
    companion object {
        fun boolean() = of(String::toBoolean)
        fun short(min: Short = Short.MIN_VALUE, max: Short = Short.MAX_VALUE) = ofRange(min, max, String::toShort)
        fun int(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE) = ofRange(min, max, String::toInt)
        fun long(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE) = ofRange(min, max, String::toLong)
        fun float(min: Float = Float.MIN_VALUE, max: Float = Float.MAX_VALUE) = ofRange(min, max, String::toFloat)
        fun double(min: Double = Double.MIN_VALUE, max: Double = Double.MAX_VALUE) = ofRange(min, max, String::toDouble)
        fun player() = of(Bukkit::getPlayer)

        fun <T> of(resolver: (String) -> T) = ArgumentResolver(resolver)
        private fun <T> ofRange(min: T, max: T, mapper: (String) -> T): ArgumentResolver<T> where T : Comparable<T>, T : Number {
            return of {
                val number = mapper(it)
                if (number < min || number > max) throw InvalidSyntaxException()
                number
            }
        }
    }

    fun resolve(arg: String): T
}