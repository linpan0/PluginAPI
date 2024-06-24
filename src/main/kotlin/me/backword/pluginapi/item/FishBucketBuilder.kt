package me.backword.pluginapi.item

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.TropicalFish
import org.bukkit.inventory.meta.TropicalFishBucketMeta

class FishBucketBuilder(amount: Int = 1) : AbstractItemBuilder<FishBucketBuilder>(Material.TROPICAL_FISH_BUCKET, amount) {
  fun bodyColor(color: DyeColor): FishBucketBuilder {
    (itemMeta as TropicalFishBucketMeta).bodyColor = color
    return this
  }

  fun patternColor(color: DyeColor): FishBucketBuilder {
    (itemMeta as TropicalFishBucketMeta).patternColor = color
    return this
  }

  fun pattern(pattern: TropicalFish.Pattern): FishBucketBuilder {
    (itemMeta as TropicalFishBucketMeta).pattern = pattern
    return this
  }
}

fun fishBucket(amount: Int = 1) = FishBucketBuilder(amount)