package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain.repository

fun getRepositoryTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package $packageName.impl.domain.repository

    internal interface ${classPrefix}Repository {

        // todo: add method
    }

""".trimIndent()
}


