package me.backword.pluginapi.messages

import me.backword.pluginapi.storage.YamlConfig
import me.backword.pluginapi.utils.colorize
import org.bukkit.command.CommandSender

fun CommandSender.message(key: String, vararg args: String) = sendMessage(Messages.getMessage(key, *args))

object Messages {
    private lateinit var messageConfig: YamlConfig

    fun setMessageConfig(messageConfig: YamlConfig) {
        this.messageConfig = messageConfig
    }

    fun getMessage(key: String, vararg args: String) = (messageConfig.reload().getString(key) ?: "").colorize().apply { args.indices.forEach { replace("{$it}", args[it]) } }
}