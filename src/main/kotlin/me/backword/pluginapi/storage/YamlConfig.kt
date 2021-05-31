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
}