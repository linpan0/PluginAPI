For Developers
------

Setup Instructions
-----
* ``git clone https://github.com/Bqckword/PluginAPI.git``
* ``cd PluginAPI``
* ``./gradlew publishToMavenLocal``

Artifact Information
-----
**NOTE:** ``x.y.z`` must be replaced with the version of PluginAPI you wish to use!
```java
repository {
  mavenLocal()
}

dependencies {
    implementation("me.backword:pluginapi:x.y.z")
}
```
