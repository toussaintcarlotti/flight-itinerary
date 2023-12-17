import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

val keyProps = Properties().apply {
    file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            // Do not change the username below. It should always be "mapbox" (not your username).
            credentials.username = "mapbox"
            // Use the secret token stored in gradle.properties as the password
            credentials.password = "sk.eyJ1IjoidG91c3NhaW50Y2FybG90dGkiLCJhIjoiY2xxODJpeGY2MWFwcjJrcGF4OGg5NGtqbSJ9.MD7IwgU3Nbstlf-2UcB1YQ"
            authentication.create<BasicAuthentication>("basic")
        }
    }
}

rootProject.name = "Flight Itinerary"
include(":app")
