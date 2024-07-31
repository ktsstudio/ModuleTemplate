package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.models

fun getUiStateModelTemplate(
    packageName: String,
    corePackageName: String,
    classPrefix: String,
): String {
    return """
    package ${packageName}.presentation.models
    
    import ${corePackageName}.common.ui.BaseUiState
    
    data class Ui${classPrefix}State(
        override val loading: Boolean = true,
        override val error: Boolean = false,
        override val details: Ui${classPrefix}Details = Ui${classPrefix}Details(),
    ) : BaseUiState<Ui${classPrefix}Details>

    data class Ui${classPrefix}Details(
        val anyParams: Any? = null, // TODO ${classPrefix.uppercase()} IMPLEMENT
    )
    """.trimIndent()
}


