package me.backword.pluginapi.commands.arguments

import me.backword.pluginapi.commands.exception.CommandException
import org.jetbrains.annotations.NotNull

class Arguments(private val args: Array<String>) {
    private var index = 0
    operator fun get(index: Int) = args[index]
    fun length() = args.size
    fun next() = args[index++]

    fun <T> next(resolver: ArgumentResolver<T>, error: String) = next(resolver, CommandException(error))
    fun <T> next(resolver: ArgumentResolver<T>, exception: CommandException) = next(resolver) ?: throw exception
    fun <T> next(resolver: ArgumentResolver<T>): T? {
        try {
            if (args.isEmpty()) return null
            return resolver.resolve(next())
        } catch (e: Throwable) {
            return null
        }
    }

    fun nextString(error: String) = nextString(CommandException(error))
    fun nextString(exception: CommandException) = nextString() ?: throw exception
    fun nextString(): String? {
        if (args.isEmpty()) return null
        return next()
    }

    @NotNull fun join(delimiter: String, error: String) = join(delimiter, CommandException(error))
    @NotNull fun join(delimiter: String, exception: CommandException) = join(delimiter) ?: exception
    fun join(delimiter: String): String? {
        val joined = args.copyOfRange(index, args.size).joinToString(delimiter).trim()
        return if (joined.isEmpty()) null else joined
    }
}