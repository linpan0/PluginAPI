package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.inventory.meta.SuspiciousStewMeta
import org.bukkit.potion.PotionEffect

class SuspiciousStewBuilder(amount: Int = 1) : AbstractItemBuilder<SuspiciousStewBuilder>(Material.SUSPICIOUS_STEW, amount) {
    fun effects(vararg effects: PotionEffect): SuspiciousStewBuilder {
        effects.forEach { (itemMeta as SuspiciousStewMeta).addCustomEffect(it, true) }
        return this
    }
}

fun suspiciousStew(amount: Int = 1) = SuspiciousStewBuilder(amount)