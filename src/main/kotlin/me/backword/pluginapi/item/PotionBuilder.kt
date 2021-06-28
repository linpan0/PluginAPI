package me.backword.pluginapi.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect

enum class PotionType(val material: Material) {
    BOTTLE(POTION), SPLASH(SPLASH_POTION), LINGERING(LINGERING_POTION)
}

class PotionBuilder(potionType: PotionType, amount: Int = 1) : AbstractItemBuilder<PotionBuilder>(potionType.material, amount) {
    fun color(color: Color): PotionBuilder {
        (itemMeta as PotionMeta).color = color
        return this
    }

    fun potionData(potionData: PotionData): PotionBuilder {
        (itemMeta as PotionMeta).basePotionData = potionData
        return this
    }

    fun effects(vararg effects: PotionEffect): PotionBuilder {
        effects.forEach { (itemMeta as PotionMeta).addCustomEffect(it, true) }
        return this
    }
}

fun potion(potionType: PotionType, amount: Int = 1) = PotionBuilder(potionType, amount)