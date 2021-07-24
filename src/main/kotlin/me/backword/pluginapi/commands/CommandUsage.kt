package me.backword.pluginapi.commands

class CommandUsage(private vararg val args: Argument) {
    internal lateinit var aliases: Array<out String>
    internal lateinit var description: String

    fun simple() = "§7/${aliases.joinToString("|")} ${args.joinToString(" ") { it.formattedName() }}"

    fun detailed() = buildString {
        appendLine("§8§m-----------§7§l/${aliases[0]} Command Help§8§m-----------")
        appendLine("§e§lAliases§7: ${aliases.drop(1).joinToString(", ")}")
        appendLine("§e§lDescription§7: $description")
        appendLine("§7§l§nArguments")
        args.map(Argument::detailed).forEach(::appendLine)
    }
}

interface Argument {
    fun formattedName(): String
    fun detailed(): String
}

class RequiredArgument(private val name: String, private val description: String) : Argument {
    override fun formattedName() = "§e<$name>"
    override fun detailed() = "§e§l$name§7: $description"
}

class OptionalArgument(private val name: String, private val description: String, private val default: String? = null) : Argument {
    override fun formattedName() = "§e($name)"
    override fun detailed() = "§e§l$name${if (default == null) "" else "§8($default)"}§7: $description"
}