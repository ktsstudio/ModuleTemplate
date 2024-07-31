package ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose

fun getComposeBuildGradleTemplate(
    packageName: String
): String {
    return """       
        plugins {
            androidCompose
        }

        android {
            namespace = "$packageName"
        }

        dependencies {
            implementation(project(":shared:main"))
            implementation(project(":android:common-ui"))
            implementation(project(":android:common-util"))
            implementation(project(":android:core-navigation"))

            implementation(Deps.KmmViewModel.core)
            implementation(Deps.KmmViewModel.cFlow)
            implementation(Deps.Koin.core)
            implementation(Deps.Koin.android)
            implementation(Deps.Koin.compose)
            composeDependency()
        }

    """.trimIndent()
}