package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BundleMeta

class BundleBuilder(amount: Int = 1) : AbstractItemBuilder<BundleBuilder>(Material.BUNDLE, amount) {
    fun items(vararg items: ItemStack): BundleBuilder {
        (itemMeta as BundleMeta).setItems(arrayListOf(*items))
        return this
    }
}

fun bundle(amount: Int = 1) = BundleBuilder(amount)