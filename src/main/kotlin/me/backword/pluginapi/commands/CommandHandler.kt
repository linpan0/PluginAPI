package me.backword.pluginapi.commands

import me.backword.pluginapi.commands.arguments.Arguments
import org.bukkit.command.CommandException
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHandler(private val command: Command, vararg aliases: String) : org.bukkit.command.Command(aliases[0]) {
    init {
        setAliases(aliases.toMutableList())
    }

    override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
        try {
            val isPlayer = sender is Player
            if (command.permissions[args.size] !== null && !sender.hasPermission(command.permissions[args.size]!!)) sender.sendMessage("§9Permission> §cNo permission!")
            else if (!isPlayer && command.playerOnly) sender.sendMessage("§9Permission> §cThis command can only be used by in-game players!")
            else if (args.size > command.maxLength) sender.sendMessage("§9Arguments> §cToo many arguments!")
            else if ((isPlayer && args.size < command.requiredPlayerLength) || (!isPlayer && args.size < command.requiredConsoleLength)) sender.sendMessage("§9Arguments> §cNot enough arguments!")
            else command.execute(sender, Arguments(args))
        } catch (e: CommandException) {
            sender.sendMessage(e.message!!)
        }

        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> {
        return command.tabComplete(sender, Arguments(args))
    }
}