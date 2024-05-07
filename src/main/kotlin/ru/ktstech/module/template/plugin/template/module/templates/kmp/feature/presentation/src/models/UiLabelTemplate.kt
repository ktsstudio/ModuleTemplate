package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.models

fun getUiLabelModelTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package ${packageName}.presentation.models
 
    import dev.icerock.moko.resources.StringResource

    sealed interface Ui${classPrefix}Label {
    
        data class Message(val messageRes: StringResource) : Ui${classPrefix}Label
    }
    """.trimIndent()
}


