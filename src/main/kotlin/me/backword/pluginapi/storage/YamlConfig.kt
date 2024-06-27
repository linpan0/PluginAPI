package me.backword.pluginapi.storage

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Files
import java.nio.file.Path

class YamlConfig(private val path: Path) {
  val data = YamlConfiguration()

  init {
    if (!Files.exists(path)) {
      Files.createDirectories(path.parent)
      val copyPath = javaClass.classLoader.getResourceAsStream(path.fileName.toString())
      if (copyPath === null) Files.createFile(path)
      else Files.copy(copyPath, path)
    }

    data.load(path.toFile())
  }

  fun save(): FileConfiguration {
    data.save(path.toFile())
    return data
  }

  fun reload(): FileConfiguration {
    data.load(path.toFile())
    return data
  }

  fun getOrCreateSection(path: String) = if (data.isConfigurationSection(path)) data.getConfigurationSection(path)!! else data.createSection(path)
  fun getOrSetLocation(key: String, default: Any) = getOrSet(key, default) { data.getLocation(key)!! }
  fun getOrSet(key: String, default: Any?) = getOrSet(key, default) { data.get(key)!! }
  fun getOrSetString(key: String, default: String) = getOrSet(key, default) { data.getString(key)!! }
  fun getOrSetInt(key: String, default: Int) = getOrSet(key, default) { data.getInt(key) }
  fun getOrSetBoolean(key: String, default: Boolean) = getOrSet(key, default) { data.getBoolean(key) }
  fun getOrSetDouble(key: String, default: Double) = getOrSet(key, default) { data.getDouble(key) }
  fun getOrSetLong(key: String, default: Long) = getOrSet(key, default) { data.getLong(key) }
  fun getOrSetStringList(key: String, default: List<String>) = getOrSet(key, default) { data.getStringList(key) }
  fun getOrSetIntegerList(key: String, default: List<Int>) = getOrSet(key, default) { data.getIntegerList(key) }
  fun getOrSetBooleanList(key: String, default: List<Boolean>) = getOrSet(key, default) { data.getBooleanList(key) }
  fun getOrSetDoubleList(key: String, default: List<Double>) = getOrSet(key, default) { data.getDoubleList(key) }
  fun getOrSetFloatList(key: String, default: List<Float>) = getOrSet(key, default) { data.getFloatList(key) }
  fun getOrSetLongList(key: String, default: List<Long>) = getOrSet(key, default) { data.getLongList(key) }
  fun getOrSetByteList(key: String, default: List<Byte>) = getOrSet(key, default) { data.getByteList(key) }
  fun getOrSetCharacterList(key: String, default: List<Char>) = getOrSet(key, default) { data.getCharacterList(key) }
  fun getOrSetShortList(key: String, default: List<Short>) = getOrSet(key, default) { data.getShortList(key) }

  private fun <T> getOrSet(key: String, default: T, mapper: (String) -> T): T {
    if (data.contains(key)) return mapper(key)
    data.set(key, default)
    return default
  }
}