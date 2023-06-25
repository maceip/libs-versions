plugins {
    `version-catalog`
    `maven-publish`
}

group = "io.github.foodiestudio"
version = "2023.01.00"

catalog {
    // declare the aliases, bundles and versions in this block
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
            pom {
                name.set("libs-versions")
                description.set("A version catalog")
                url.set("https://github.com/foodiestudio/libs-versions")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/foodiestudio/libs-versions/blob/main/LICENSE")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"

            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"

            setUrl(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}