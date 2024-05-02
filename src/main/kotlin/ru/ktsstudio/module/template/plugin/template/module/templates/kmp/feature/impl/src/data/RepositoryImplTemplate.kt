package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.impl.src.data

fun getRepositoryImplTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package $packageName.impl.data
    
    import $packageName.impl.domain.repository.${classPrefix}Repository

    internal class ${classPrefix}RepositoryImpl : ${classPrefix}Repository {

        // todo: override methods
    }

""".trimIndent()
}


