package me.backword.pluginapi.commands

class CommandUsage(private val description: String, private vararg val args: Argument) {
  internal lateinit var aliases: Array<out String>

  fun formatted() = "§e/${aliases[0]}§7: $description"

  fun simple() = "§7/${aliases.joinToString("|")} ${args.joinToString(" ") { it.formattedName }}"

  fun detailed() = buildString {
    appendLine("§8§m-----------§7§l/${aliases[0]} Command Help§8§m-----------")
    if (aliases.size >= 2) appendLine("§e§lAliases§7: ${aliases.drop(1).joinToString(", ")}")
    appendLine("§e§lDescription§7: $description")
    if (args.isEmpty()) return@buildString
    appendLine("§7§lArguments:")
    args.map(Argument::detailed).forEach(::appendLine)
  }
}

interface Argument {
  val formattedName: String
  val detailed: String
}

class RequiredArgument(name: String, description: String) : Argument {
  override val formattedName = "§e<$name>"
  override val detailed = "§e$name§7: $description"
}

class OptionalArgument(name: String, description: String, default: String? = null) : Argument {
  override val formattedName = "§e($name)"
  override val detailed = "§e$name${if (default == null) "" else "§8($default)"}§7: $description"
}