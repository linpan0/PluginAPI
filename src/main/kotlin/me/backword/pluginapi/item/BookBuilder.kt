package me.backword.pluginapi.item

import me.backword.pluginapi.color.colorize
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.meta.BookMeta

enum class BookType(val material: Material) {
  WRITTEN(Material.WRITTEN_BOOK), WRITABLE(Material.WRITABLE_BOOK)
}

class BookBuilder(bookType: BookType, amount: Int = 1) : AbstractItemBuilder<BookBuilder>(bookType.material, amount) {
  fun author(author: String): BookBuilder {
    (itemMeta as BookMeta).author = author.colorize()
    return this
  }

  fun generation(generation: BookMeta.Generation): BookBuilder {
    (itemMeta as BookMeta).generation = generation
    return this
  }

  fun title(title: String): BookBuilder {
    (itemMeta as BookMeta).title = title.colorize()
    return this
  }

  fun pages(vararg pages: Array<String>): BookBuilder {
    pages.map { Component.text(it.toString().colorize()) }.forEach { (itemMeta as BookMeta).addPages(it) }
    return this
  }
}

fun book(bookType: BookType, amount: Int = 1) = BookBuilder(bookType, amount)