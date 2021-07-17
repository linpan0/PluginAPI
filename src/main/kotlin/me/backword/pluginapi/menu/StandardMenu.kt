package me.backword.pluginapi.menu

import me.backword.pluginapi.color.colorize
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class StandardMenu(private val name: String, private val size: Int) : Menu {
    private val buttons = HashMap<Int, Button>(size)

    fun button(itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = { it.isCancelled = true } ) {
        val firstEmpty = (0..size).firstOrNull { buttons[it] === null }
        if (firstEmpty !== null)
            buttons[firstEmpty] = Button(itemStack, action)
    }

    fun button(index: Int, itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = { it.isCancelled = true } ) = buttons.set(index, Button(itemStack, action))

    fun button(x: Int, y: Int, itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = { it.isCancelled = true } ) = buttons.set(y * 9 + x, Button(itemStack, action))

    override fun getButton(slot: Int) = buttons[slot]

    override fun getInventory() = Bukkit.createInventory(this, size, Component.text(name.colorize())).apply {
        buttons.entries.take(size).forEach { button -> setItem(button.key, button.value.itemStack) }
    }
}