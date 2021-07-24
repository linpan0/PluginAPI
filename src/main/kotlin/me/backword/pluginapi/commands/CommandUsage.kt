package me.backword.pluginapi.commands

class CommandUsage(private vararg val args: Argument) {
    internal lateinit var aliases: Array<out String>
    internal lateinit var description: String

    fun simple() = "§7/${aliases.joinToString("|")} ${args.joinToString(" ") { it.formattedName() }}"

    fun detailed() = buildString {
        appendLine("§7§m----------§7§l/${aliases[0]} Command Help§7§m----------")
        appendLine("§eAliases: §7${aliases.drop(1).joinToString(", ")}")
        appendLine("§eDescription: §7$description")
        appendLine("§7§nArguments")
        args.map(Argument::detailed).forEach(::appendLine)
        appendLine("§7§m--------------------------------------------------")
    }
}

interface Argument {
    fun formattedName(): String
    fun detailed(): String
}

class RequiredArgument(private val name: String, private val description: String) : Argument {
    override fun formattedName() = "§e<$name>"
    override fun detailed() = "§e$name: §7$description"
}

class OptionalArgument(private val name: String, private val description: String, private val default: String? = null) : Argument {
    override fun formattedName() = "§e($name)"
    override fun detailed() = "§e$name${if (default == null) "" else "($default)"}: §7$description"
}