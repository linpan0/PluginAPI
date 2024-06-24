package me.backword.pluginapi.events

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.Plugin

interface Events : Listener, EventExecutor

inline fun <T : Event> listen(plugin: Plugin, type: Class<T>, priority: EventPriority = EventPriority.NORMAL, crossinline listener: (T) -> Unit) {
  val events = object : Events {
    override fun execute(ignored: Listener, event: Event) {
      if (!type.isInstance(event)) return
      listener(event as T)
    }
  }

  Bukkit.getPluginManager().registerEvent(type, events, priority, events, plugin)
}

inline fun <reified T : Event> listen(plugin: Plugin, priority: EventPriority = EventPriority.NORMAL, crossinline listener: (T) -> Unit) = listen(plugin, T::class.java, priority, listener)

fun <T : Event> callEvent(event: T) = Bukkit.getPluginManager().callEvent(event)