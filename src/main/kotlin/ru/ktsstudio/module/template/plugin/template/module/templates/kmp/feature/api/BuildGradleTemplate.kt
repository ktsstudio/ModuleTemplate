package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.api

fun getApiBuildGradleTemplate(
    packageName: String
): String {
    return """       
        plugins {
            id("multiplatform-feature-setup")
        }
        
        kotlin {
            sourceSets {
                commonMain {
                    implementations(
                        project(":shared:core-db"),
                        Deps.KmmViewModel.core,
                        Deps.KmmViewModel.cFlow,
                        Deps.Kotlin.immutableСollections,
                    )
                }
            }
        }
        
        android {
            namespace = "$packageName.api"
        }
    """.trimIndent()
}