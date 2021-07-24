package me.backword.pluginapi.commands

class CommandUsage(private vararg val args: Argument) {
    internal lateinit var aliases: Array<out String>
    internal lateinit var description: String

    fun simple() = "§7/${aliases.joinToString("|")} ${args.joinToString(" ") { it.formattedName() }}"

    fun detailed() = buildString {
        appendLine("§l§f/${aliases[0]} Command Help")
        appendLine("§eAliases: §7${aliases.drop(0).joinToString(", ")}")
        appendLine("§eDescription: §7$description")
        appendLine("§n§fArguments")
        args.map(Argument::detailed).forEach(::appendLine)
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

class OptionalArgument(private val name: String, private val description: String, private val default: String) : Argument {
    override fun formattedName() = "§e($name)"
    override fun detailed() = "§e$name($default): §7$description"
}