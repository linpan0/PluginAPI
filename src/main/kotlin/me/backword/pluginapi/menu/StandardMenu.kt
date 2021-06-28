package me.backword.pluginapi.menu

import me.backword.pluginapi.color.colorize
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

fun menu(builder: StandardMenu.MenuBuilder.() -> Unit) = StandardMenu.MenuBuilder().apply(builder).build()

open class StandardMenu(private val name: String, private val size: Int) : Menu {
    private val buttons = HashMap<Int, Button>(size)

    fun button(button: Button) {
        val slot = buttons.keys.firstOrNull { buttons[it] !== null }
        if (slot === null) buttons[buttons.size] = button
        else buttons[slot] = button
    }

    fun button(index: Int, button: Button) = buttons.set(index, button)

    // Clicked item should never be null(so not Material.AIR), so a button theoretically should always exist
    override fun getButton(slot: Int) = buttons[slot]!!

    override fun getInventory() = Bukkit.createInventory(this, size, Component.text(name.colorize())).apply {
        buttons.entries.take(size).forEach { button -> setItem(button.key, button.value.itemStack) }
    }

    class MenuBuilder {
        lateinit var name: String
        var size = 0
        private val buttons = arrayListOf<Button>()

        fun buttons(builder: ButtonListBuilder.() -> Unit) {
            buttons.addAll(ButtonListBuilder().apply(builder).build())
        }

        fun build() = StandardMenu(name, size).apply {
            for (i in buttons.keys) {
                val button = buttons[i] ?: continue
                button(i, button)
            }
        }
    }

    class ButtonListBuilder {
        private val buttons = arrayListOf<Button>()

        fun button(slot: Int, builder: ButtonBuilder.() -> Unit) {
            buttons[slot] = ButtonBuilder().apply(builder).build()
        }

        fun button(builder: ButtonBuilder.() -> Unit) {
            buttons.add(ButtonBuilder().apply(builder).build())
        }

        fun build() = buttons
    }

    class ButtonBuilder {
        lateinit var itemStack: ItemStack
        private lateinit var onClick: (InventoryClickEvent) -> Unit

        fun onClick(action: (InventoryClickEvent) -> Unit) {
            onClick = action
        }

        fun build() = Button(itemStack, onClick)
    }
}