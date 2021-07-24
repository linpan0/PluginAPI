package me.backword.pluginapi.commands

import me.backword.pluginapi.commands.arguments.Arguments
import net.kyori.adventure.text.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

abstract class Command {
    open val playerOnly = false
    open val minPlayerLength = 0
    open val minConsoleLength = 0
    open val maxLength = 1
    abstract val usage: CommandUsage
    val permissions = hashMapOf<Int, String>()

    abstract fun run(sender: CommandSender, args: Arguments)
    open fun tabComplete(sender: CommandSender, args: Arguments) = mutableListOf<String>()

    companion object {
        @JvmStatic protected fun allPlayerNames() = Bukkit.getOnlinePlayers().map { (it.displayName() as TextComponent).content() }.toMutableList()
    }
}