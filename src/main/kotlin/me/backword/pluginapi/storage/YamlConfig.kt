package me.backword.pluginapi.storage

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Files
import java.nio.file.Path

class YamlConfig(private val path: Path) {
    val config = YamlConfiguration()

    init {
        if (!Files.exists(path)) {
            Files.createDirectories(path.parent)
            val copyPath = javaClass.classLoader.getResourceAsStream(path.fileName.toString())
            if (copyPath === null) Files.createFile(path)
            else Files.copy(copyPath, path)
        }

        config.load(path.toFile())
    }

    fun save(): FileConfiguration {
        config.save(path.toFile())
        return config
    }

    fun reload(): FileConfiguration {
        config.load(path.toFile())
        return config
    }
}