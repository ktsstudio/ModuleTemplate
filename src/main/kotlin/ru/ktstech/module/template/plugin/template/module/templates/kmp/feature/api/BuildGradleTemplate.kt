package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.api

fun getApiBuildGradleTemplate(
    packageName: String
): String {
    return """       
        plugins {
            id("multiplatform-feature-setup")
        } 
        
        android {
            namespace = "$packageName.api"
        }
    """.trimIndent()
}