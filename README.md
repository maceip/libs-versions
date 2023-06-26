## version catalog
provide common version catalog

### Usage

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        //...
        mavenCentral()
    }
    versionCatalogs {
        create("sharedLibs") {
            from("io.github.foodiestudio:libs-versions:$version")
        }
    }
}
```

```groovy
// build.gradle (module)
dependencies {
    // We can reference it in
    implementation sharedLibs.ctc
}
```
