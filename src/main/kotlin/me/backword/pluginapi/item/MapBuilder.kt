package me.backword.pluginapi.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapView

class MapBuilder(amount: Int = 1) : AbstractItemBuilder<MapBuilder>(Material.MAP, amount) {
    fun color(color: Color): MapBuilder {
        (itemMeta as MapMeta).color = color
        return this
    }

    fun mapView(mapView: MapView): MapBuilder {
        (itemMeta as MapMeta).mapView = mapView
        return this
    }

    fun scaling(scaling: Boolean): MapBuilder {
        (itemMeta as MapMeta).isScaling = scaling
        return this
    }

    fun locationName(name: String): MapBuilder {
        (itemMeta as MapMeta).locationName = name
        return this
    }
}

fun map(amount: Int = 1) = MapBuilder(amount)