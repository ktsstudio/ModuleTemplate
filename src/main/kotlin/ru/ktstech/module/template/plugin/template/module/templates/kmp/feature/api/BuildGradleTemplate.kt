package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.api

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