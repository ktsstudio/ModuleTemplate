package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation

fun getPresentationBuildGradleTemplate(
    packageName: String
): String {
    return """
        plugins {
            id("multiplatform-feature-setup")
        } 
        
        android {
            namespace = "$packageName.presentation"
        } 
    """.trimIndent()
}