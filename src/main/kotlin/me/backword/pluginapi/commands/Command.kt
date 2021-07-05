package me.backword.pluginapi.commands

import me.backword.pluginapi.commands.arguments.Arguments
import org.bukkit.command.CommandSender

abstract class Command {
    open val playerOnly = false
    open val minPlayerLength = 0
    open val minConsoleLength = 0
    open val maxLength = 1
    open val description = ""
    open val usage = ""
    val permissions = hashMapOf<Int, String>()

    abstract fun run(sender: CommandSender, args: Arguments)
    open fun tabComplete(sender: CommandSender, args: Arguments) = mutableListOf<String>()
}