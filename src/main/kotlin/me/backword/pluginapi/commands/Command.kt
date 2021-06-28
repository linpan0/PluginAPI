package me.backword.pluginapi.commands

import me.backword.pluginapi.commands.arguments.Arguments
import org.bukkit.command.CommandSender

abstract class Command(val playerOnly: Boolean, val requiredPlayerLength: Int, val requiredConsoleLength: Int, val maxLength: Int) {
    val permissions = hashMapOf<Int, String>()
    abstract fun run(sender: CommandSender, args: Arguments)
    open fun tabComplete(sender: CommandSender, args: Arguments) = mutableListOf<String>()
}