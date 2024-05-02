package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain

import ru.ktsstudio.module.template.plugin.common.appendIf

fun getMVIStoreFactoryTemplate(
    packageName: String,
    classPrefix: String,
    withRepository: Boolean,
): String {
    return """
    package $packageName.impl.domain
    
    import com.arkivanov.mvikotlin.core.store.Store
    import com.arkivanov.mvikotlin.core.store.StoreFactory
    import ${packageName}.api.domain.${classPrefix}Store
    import ${packageName}.api.domain.${classPrefix}Store.Intent
    import ${packageName}.api.domain.${classPrefix}Store.Label
    import ${packageName}.api.domain.${classPrefix}Store.State 
   ${"import ${packageName}.impl.domain.repository.${classPrefix}Repository".appendIf(withRepository)}

    internal class ${classPrefix}StoreFactory(
        private val storeFactory: StoreFactory,
        ${"private val ${classPrefix.lowercase()}Repository: ${classPrefix}Repository".appendIf(withRepository)}
    ) {
        fun create(): ${classPrefix}Store = object : ${classPrefix}Store,
            Store<Intent, State, Label> by storeFactory.create(
                name = ${classPrefix}Store::class.simpleName,
                initialState = State.Default,
                bootstrapper = null,
                executorFactory = {
                    ${classPrefix}Executor(${"${classPrefix.lowercase()}Repository = ${classPrefix.lowercase()}Repository".appendIf(withRepository)}),
                },
                reducer = ${classPrefix}Reducer(),
            ) {}

        sealed interface Message {
            data class SetLoading(val loading: Boolean) : Message
            data class Error(val throwable: Throwable) : Message
        }
    }

""".trimIndent()
}

