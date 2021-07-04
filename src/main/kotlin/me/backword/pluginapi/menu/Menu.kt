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
                if (event.currentItem === null) return@listen
                val menu = event.clickedInventory?.holder as? Menu ?: return@listen
                val action = menu.getButton(event.slot)?.action ?: return@listen
                action(event)
            }
        }
    }

    fun getButton(slot: Int): Button?
    fun onOpen(event: InventoryOpenEvent) {}
    fun onClose(event: InventoryCloseEvent) {}
}