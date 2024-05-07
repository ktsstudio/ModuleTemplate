package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.di

import ru.ktstech.module.template.plugin.common.appendIf

fun getImplKoinTemplate(
    packageName: String,
    classPrefix: String,
    withMvi: Boolean,
    withRepository: Boolean,
): String {
    return """
    package $packageName.impl.di
     
    import org.koin.dsl.module
    ${"import ${packageName}.impl.domain.${classPrefix}StoreFactory".appendIf(withMvi)}
    ${"import ${packageName}.impl.domain.repository.${classPrefix}Repository".appendIf(withRepository)}
    ${"import ${packageName}.impl.data.${classPrefix}RepositoryImpl".appendIf(withRepository)}
   
    val feature${classPrefix}ImplModule = module {
         ${
            """
            factory<${classPrefix}Repository> {
                ${classPrefix}RepositoryImpl()
            }
             """.trimIndent().appendIf(withRepository)
        }
        ${
        """
        factory {
            ${classPrefix}StoreFactory(
                storeFactory = get(),
                ${"${classPrefix.lowercase()}Repository = get(),".appendIf(withRepository)}
            ).create()
        }
         """.trimIndent().appendIf(withMvi)
        }
    }
""".trimIndent()
}