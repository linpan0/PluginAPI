package me.backword.pluginapi.item

import org.bukkit.Material
import org.bukkit.Material.*
import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.meta.BannerMeta

enum class BannerColor(val material: Material) {
    BLACK(BLACK_BANNER), BLUE(BLUE_BANNER), BROWN(BROWN_BANNER), CYAN(CYAN_BANNER), GRAY(GRAY_BANNER), GREEN(GREEN_BANNER), LIGHT_BLUE(LIGHT_BLUE_BANNER), LIME(LIME_BANNER), MAGENTA(MAGENTA_BANNER), ORANGE(ORANGE_BANNER), PINK(PINK_BANNER), PURPLE(PURPLE_BANNER), RED(RED_BANNER), WHITE(WHITE_BANNER), YELLOW(YELLOW_BANNER)
}

class BannerBuilder(color: BannerColor, amount: Int = 1) : AbstractItemBuilder<BannerBuilder>(color.material, amount) {
    fun patterns(vararg patterns: Pattern): BannerBuilder {
        (itemMeta as BannerMeta).patterns = patterns.asList()
        return this
    }

    fun pattern(index: Int, pattern: Pattern): BannerBuilder {
        (itemMeta as BannerMeta).setPattern(index, pattern)
        return this
    }
}

fun banner(color: BannerColor, amount: Int = 1) = BannerBuilder(color, amount)