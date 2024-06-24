package me.backword.pluginapi.item

import io.papermc.paper.potion.SuspiciousEffectEntry
import org.bukkit.Material
import org.bukkit.inventory.meta.SuspiciousStewMeta

class SuspiciousStewBuilder(amount: Int = 1) : AbstractItemBuilder<SuspiciousStewBuilder>(Material.SUSPICIOUS_STEW, amount) {
  fun effects(vararg effects: SuspiciousEffectEntry): SuspiciousStewBuilder {
    effects.forEach { (itemMeta as SuspiciousStewMeta).addCustomEffect(it, true) }
    return this
  }
}

fun suspiciousStew(amount: Int = 1) = SuspiciousStewBuilder(amount)