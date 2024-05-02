package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.impl

fun getImplBuildGradleTemplate(
    packageName: String,
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
            namespace = "$packageName.impl"
        }
    """.trimIndent()
}