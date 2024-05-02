package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.presentation.src.models

fun getUiStateModelTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package ${packageName}.presentation.models
    
    data class Ui${classPrefix}State(
        val loading: Boolean = true,
        val error: Throwable? = null, 
    )
    """.trimIndent()
}


