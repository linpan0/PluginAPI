package me.backword.pluginapi.reflection

import org.bukkit.Bukkit

private const val NMS_CLASS_FORMAT = "net.minecraft.server.%s.%s"
private const val BUKKIT_CLASS_FORMAT = "org.bukkit.craftbukkit.%s.%s"

fun nms(path: String) = Class.forName(String.format(NMS_CLASS_FORMAT, getVersion(), path))
fun bukkit(path: String) = Class.forName(String.format(BUKKIT_CLASS_FORMAT, getVersion(), path))
fun getVersion() = Bukkit.getServer()::class.java.`package`.name.split(".")[3]