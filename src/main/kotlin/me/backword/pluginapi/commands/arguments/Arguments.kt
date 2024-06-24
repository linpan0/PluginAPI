package me.backword.pluginapi.commands.arguments

import me.backword.pluginapi.commands.exception.InvalidSyntaxException

class Arguments(private val args: Array<String>) {
  private var index = 0
  val length = args.size

  operator fun get(index: Int) = args[index]
  fun hasNext() = index < args.size

  fun next(error: String) = next() ?: throw InvalidSyntaxException(error)
  fun next() = if (hasNext()) args[index++] else null

  fun <T> next(resolver: ArgumentResolver<T>, error: String) = next(resolver, InvalidSyntaxException(error))
  fun <T> next(resolver: ArgumentResolver<T>, exception: InvalidSyntaxException) = next(resolver) ?: throw exception
  fun <T> next(resolver: ArgumentResolver<T>): T? {
    try {
      if (args.isEmpty()) return null
      val next = next() ?: return null
      return resolver.resolve(next)
    } catch (e: Throwable) {
      return null
    }
  }

  fun join(delimiter: String, error: String) = join(delimiter, InvalidSyntaxException(error))
  fun join(delimiter: String, exception: InvalidSyntaxException) = join(delimiter) ?: throw exception
  fun join(delimiter: String) = args.copyOfRange(index, args.size).joinToString(delimiter).trim().ifEmpty { null }
}