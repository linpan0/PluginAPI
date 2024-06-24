package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.meta.EnchantmentStorageMeta

class EnchantBookBuilder(amount: Int = 1) : AbstractItemBuilder<EnchantBookBuilder>(Material.ENCHANTED_BOOK, amount) {
  fun storedEnchant(enchant: Enchantment, level: Int = 1): EnchantBookBuilder {
    (itemMeta as EnchantmentStorageMeta).addStoredEnchant(enchant, level, true)
    return this
  }
}

fun enchantBook(amount: Int = 1) = EnchantBookBuilder(amount)