package me.backword.pluginapi.storage

import org.bukkit.configuration.file.YamlConfiguration

fun YamlConfiguration.getOrCreateSection(path: String) = if (isConfigurationSection(path)) getConfigurationSection(path)!! else createSection(path)

fun YamlConfiguration.getOrSet(key: String, default: Any?) = getOrSet(key, default) { get(key) }
fun YamlConfiguration.getOrSetString(key: String, default: String) = getOrSet( key, default) { getString(key) }
fun YamlConfiguration.getOrSetInt(key: String, default: Int) = getOrSet(key, default) { getInt(key) }
fun YamlConfiguration.getOrSetBoolean(key: String, default: Boolean) = getOrSet(key, default) { getBoolean(key) }
fun YamlConfiguration.getOrSetDouble(key: String, default: Double) = getOrSet(key, default) { getDouble(key) }
fun YamlConfiguration.getOrSetLong(key: String, default: Long) = getOrSet(key, default) { getLong(key) }
fun YamlConfiguration.getOrSetStringList(key: String, default: List<String>) = getOrSet(key, default) { getStringList(key) }
fun YamlConfiguration.getOrSetIntegerList(key: String, default: List<Int>) = getOrSet(key, default) { getIntegerList(key) }
fun YamlConfiguration.getOrSetBooleanList(key: String, default: List<Boolean>) = getOrSet(key, default) { getBooleanList(key) }
fun YamlConfiguration.getOrSetDoubleList(key: String, default: List<Double>) = getOrSet(key, default) { getDoubleList(key) }
fun YamlConfiguration.getOrSetFloatList(key: String, default: List<Float>) = getOrSet(key, default) { getFloatList(key) }
fun YamlConfiguration.getOrSetLongList(key: String, default: List<Long>) = getOrSet(key, default) { getLongList(key) }
fun YamlConfiguration.getOrSetByteList(key: String, default: List<Byte>) = getOrSet(key, default) { getByteList(key) }
fun YamlConfiguration.getOrSetCharacterList(key: String, default: List<Char>) = getOrSet(key, default) { getCharacterList(key) }
fun YamlConfiguration.getOrSetShortList(key: String, default: List<Short>) = getOrSet(key, default) { getShortList(key) }
fun <T> YamlConfiguration.getOrSet(key: String, default: T, mapper: (String) -> T): T {
    if (contains(key)) return mapper(key)
    set(key, default)
    return default
}