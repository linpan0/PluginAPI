package me.backword.pluginapi.menu

import me.backword.pluginapi.events.listen
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.Plugin

interface Menu : InventoryHolder {
    companion object {
        fun listen(plugin: Plugin) {
            listen<InventoryClickEvent>(plugin) { event ->
                if (event.clickedInventory === null) return@listen
                val menu = event.clickedInventory?.holder as? Menu ?: return@listen
                menu.onClick(event)
                val action = menu.getButton(event.slot)?.action ?: return@listen
                action(event)
            }

            listen<InventoryOpenEvent>(plugin) { event ->
                val menu = event.inventory.holder as? Menu ?: return@listen
                menu.onOpen(event)
            }

            listen<InventoryCloseEvent>(plugin) { event ->
                val menu = event.inventory.holder as? Menu ?: return@listen
                menu.onClose(event)
            }
        }
    }

    fun getButton(slot: Int): Button?
    fun onClick(event: InventoryClickEvent) {}
    fun onOpen(event: InventoryOpenEvent) {}
    fun onClose(event: InventoryCloseEvent) {}
}