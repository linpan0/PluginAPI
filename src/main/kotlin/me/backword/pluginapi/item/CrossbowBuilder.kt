package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CrossbowMeta

class CrossbowBuilder(amount: Int = 1) : AbstractItemBuilder<CrossbowBuilder>(Material.CROSSBOW, amount) {
  fun projectiles(vararg projectiles: ItemStack): CrossbowBuilder {
    (itemMeta as CrossbowMeta).setChargedProjectiles(projectiles.toList())
    return this
  }
}

fun crossbow(amount: Int = 1) = CrossbowBuilder(amount)