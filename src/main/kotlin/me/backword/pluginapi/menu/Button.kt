package me.backword.pluginapi.menu

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class Button(val itemStack: ItemStack, val action: (InventoryClickEvent) -> Unit)