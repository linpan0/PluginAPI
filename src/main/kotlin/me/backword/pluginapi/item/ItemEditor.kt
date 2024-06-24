package me.backword.pluginapi.item

import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

// TODO: 7/4/2021 add more functions here

fun <T : ItemMeta> ItemStack.editMeta(action: (T) -> Unit) {
  val meta = itemMeta as T
  action(meta)
  itemMeta = meta
}

fun ItemStack.editLore(action: (MutableList<Component>) -> Unit) = editMeta<ItemMeta> { meta ->
  val lore = meta.lore() ?: arrayListOf()
  action(lore)
  meta.lore(lore)
}

fun ItemStack.addLore(component: Component) = editLore { it.add(component) }

fun ItemStack.setLore(index: Int, component: Component) = editLore { it[index] = component }

fun ItemStack.removeLore(index: Int) = editLore { it.removeAt(index) }