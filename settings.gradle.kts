dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
rootProject.name = ("VolvoCarsAssignment")
include(
    ":app",
    ":core:navigation",
    ":features:home",
    ":features:details",
)
