package me.backword.pluginapi.item

import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.meta.FireworkMeta

class FireworkBuilder(amount: Int = 1) : AbstractItemBuilder<FireworkBuilder>(Material.FIREWORK_ROCKET, amount) {
  fun power(power: Int): FireworkBuilder {
    (itemMeta as FireworkMeta).power = power
    return this
  }

  fun effects(vararg effects: FireworkEffect): FireworkBuilder {
    (itemMeta as FireworkMeta).addEffects(*effects)
    return this
  }
}

fun firework(amount: Int = 1) = FireworkBuilder(amount)