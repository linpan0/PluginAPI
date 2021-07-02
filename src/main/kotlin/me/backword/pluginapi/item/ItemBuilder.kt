package me.backword.pluginapi.item

import org.bukkit.Material

class ItemBuilder(material: Material, amount: Int = 1) : AbstractItemBuilder<ItemBuilder>(material, amount)

fun item(material: Material, amount: Int = 1) = ItemBuilder(material, amount)