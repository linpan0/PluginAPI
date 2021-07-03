For Developers
------

Build Instructions:
-----
* ``git clone https://github.com/Bqckword/PluginAPI.git``
* ``cd PluginAPI``
* ``./gradlew publishToMavenLocal``

* Artifact Information:
```java
repository {
  mavenLocal()
}

dependencies {
    implementation("me.backword:pluginapi:x.y.z")
}
```
