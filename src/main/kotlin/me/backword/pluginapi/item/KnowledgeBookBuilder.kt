package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.KnowledgeBookMeta

class KnowledgeBookBuilder(amount: Int = 1) : AbstractItemBuilder<KnowledgeBookBuilder>(Material.KNOWLEDGE_BOOK, amount) {
    fun recipes(vararg recipes: NamespacedKey): KnowledgeBookBuilder {
        (itemMeta as KnowledgeBookMeta).recipes = recipes.toList()
        return this
    }
}

fun knowledgeBook(amount: Int = 1) = KnowledgeBookBuilder(amount)