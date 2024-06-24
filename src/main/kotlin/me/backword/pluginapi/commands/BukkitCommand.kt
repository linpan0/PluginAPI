package me.backword.pluginapi.commands

import me.backword.pluginapi.commands.arguments.Arguments
import me.backword.pluginapi.commands.exception.InvalidSyntaxException
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BukkitCommand(private val command: Command, vararg aliases: String) : org.bukkit.command.Command(aliases[0]) {
  init {
    setAliases(aliases.toMutableList())
  }

  override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
    try {
      val isPlayer = sender is Player
      when {
        command.permissions[args.size] !== null && !sender.hasPermission(command.permissions[args.size]!!) -> sender.sendMessage(
          "§9Permission> §cNo permission!"
        )

        !isPlayer && command.playerOnly -> sender.sendMessage("§9Permission> §cThis command can only be used by in-game players!")
        args.size > command.maxLength -> sender.sendMessage("§9Usage> §cToo many arguments! ${command.usage.simple()}")
        isPlayer && args.size < command.minPlayerLength || !isPlayer && args.size < command.minConsoleLength -> sender.sendMessage(
          "§9Usage> §cNot enough arguments! ${command.usage.simple()}"
        )

        else -> command.run(sender, Arguments(args))
      }
    } catch (e: InvalidSyntaxException) {
      sender.sendMessage(e.message!!)
    }

    return true
  }

  override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>) =
    command.tabComplete(sender, Arguments(args))
}