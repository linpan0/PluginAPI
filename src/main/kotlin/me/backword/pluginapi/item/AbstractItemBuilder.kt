package me.backword.pluginapi.item

import me.backword.pluginapi.color.colorize
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer

abstract class AbstractItemBuilder<T : AbstractItemBuilder<T>>(material: Material, amount: Int = 1) {
    private val itemStack = ItemStack(material, amount)
    protected val itemMeta = Bukkit.getItemFactory().getItemMeta(material)!!

    fun name(name: String): T {
        itemMeta.displayName(Component.text(name.colorize()))
        return this as T
    }

    fun lore(vararg lore: String): T {
        itemMeta.lore(lore.map { Component.text(it.colorize()) })
        return this as T
    }

    fun enchant(enchant: Enchantment, level: Int = 1): T {
        itemMeta.addEnchant(enchant, level, true)
        return this as T
    }

    fun flags(vararg itemFlags: ItemFlag): T {
        itemMeta.addItemFlags(*itemFlags)
        return this as T
    }

    fun allFlags(): T {
        itemMeta.addItemFlags(*ItemFlag.values())
        return this as T
    }

    fun modifier(attribute: Attribute, modifier: AttributeModifier): T {
        itemMeta.addAttributeModifier(attribute, modifier)
        return this as T
    }

    fun unbreakable(isUnbreakable: Boolean): T {
        itemMeta.isUnbreakable = isUnbreakable
        return this as T
    }

    fun data(action: (PersistentDataContainer) -> Unit): T {
        action(itemMeta.persistentDataContainer)
        return this as T
    }

    fun build() = itemStack.apply { itemMeta = this.itemMeta }
}