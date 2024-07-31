package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl

fun getImplBuildGradleTemplate(
    packageName: String,
): String {
    return """       
        plugins {
            id("multiplatform-feature-setup")
        } 
        
        android {
            namespace = "$packageName.impl"
        }
    """.trimIndent()
}