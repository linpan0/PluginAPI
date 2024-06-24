package me.backword.pluginapi.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.inventory.meta.LeatherArmorMeta

enum class LeatherType(val material: Material) {
  HELMET(LEATHER_HELMET), CHESTPLATE(LEATHER_CHESTPLATE), LEGGINGS(LEATHER_LEGGINGS), BOOTS(LEATHER_BOOTS)
}

class LeatherArmorBuilder(leatherType: LeatherType, amount: Int = 1) : AbstractItemBuilder<LeatherArmorBuilder>(leatherType.material, amount) {
  fun color(color: Color): LeatherArmorBuilder {
    (itemMeta as LeatherArmorMeta).setColor(color)
    return this
  }
}

fun leatherArmor(leatherType: LeatherType, amount: Int = 1) = LeatherArmorBuilder(leatherType, amount)