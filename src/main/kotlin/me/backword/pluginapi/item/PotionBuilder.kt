package me.backword.pluginapi.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionType

enum class PotionBottleType(val material: Material) {
  BOTTLE(POTION), SPLASH(SPLASH_POTION), LINGERING(LINGERING_POTION)
}

class PotionBuilder(potionType: PotionBottleType, amount: Int = 1) : AbstractItemBuilder<PotionBuilder>(potionType.material, amount) {
  fun color(color: Color): PotionBuilder {
    (itemMeta as PotionMeta).color = color
    return this
  }

  fun potionType(potionType: PotionType): PotionBuilder {
    (itemMeta as PotionMeta).basePotionType = potionType
    return this
  }

  fun effects(vararg effects: PotionEffect): PotionBuilder {
    effects.forEach { (itemMeta as PotionMeta).addCustomEffect(it, true) }
    return this
  }
}

fun potion(potionType: PotionBottleType, amount: Int = 1) = PotionBuilder(potionType, amount)