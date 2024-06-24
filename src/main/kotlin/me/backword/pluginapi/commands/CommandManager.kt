package me.backword.pluginapi.commands

import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.plugin.Plugin

class CommandManager(private val plugin: Plugin) {
  companion object {
    private val COMMAND_MAP_FIELD =
      Bukkit.getServer()::class.java.getDeclaredField("commandMap").apply { isAccessible = true }
  }

  private val commandMap = COMMAND_MAP_FIELD.get(Bukkit.getServer()) as CommandMap

  fun registerCommand(command: Command, vararg aliases: String) {
    command.usage.aliases = aliases
    commandMap.register(aliases[0], plugin.name, BukkitCommand(command, *aliases))
  }
}