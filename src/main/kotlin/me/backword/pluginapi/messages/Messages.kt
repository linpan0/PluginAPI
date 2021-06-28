package me.backword.pluginapi.messages

import me.backword.pluginapi.storage.YamlConfig
import me.backword.pluginapi.color.colorize
import org.bukkit.command.CommandSender

fun CommandSender.message(key: String, vararg args: String) = sendMessage(Messages.getMessage(key, *args))

object Messages {
    private lateinit var messageConfig: YamlConfig

    fun setMessageConfig(messageConfig: YamlConfig) {
        this.messageConfig = messageConfig
    }

    fun getMessage(key: String, vararg args: String): String {
        var message = messageConfig.reload().getString(key)?.colorize() ?: return ""
        for (i in args.indices) {
            message = message.replace("{$i}", args[i])
        }

        return message
    }
}