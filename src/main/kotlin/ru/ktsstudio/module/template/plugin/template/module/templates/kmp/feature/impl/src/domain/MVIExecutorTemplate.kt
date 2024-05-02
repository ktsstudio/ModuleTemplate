package ru.ktsstudio.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain

import ru.ktsstudio.module.template.plugin.common.appendIf

fun getMVIExecutorTemplate(
    packageName: String,
    classPrefix: String,
    withRepository: Boolean,
): String {
    return """
    package $packageName.impl.domain

    import ${packageName}.api.domain.${classPrefix}Store.Intent
    import ${packageName}.api.domain.${classPrefix}Store.Label
    import ${packageName}.api.domain.${classPrefix}Store.State
    import ${packageName}.impl.domain.${classPrefix}StoreFactory.Message
    ${"import ${packageName}.impl.domain.repository.${classPrefix}Repository".appendIf(withRepository)}
    import ru.ktsstudio.kotlin_utils.mvi.BaseExecutor 

    internal class ${classPrefix}Executor${"""(
        private val ${classPrefix.lowercase()}Repository: ${classPrefix}Repository,
    )""".appendIf(withRepository)} : BaseExecutor<Intent, Nothing, State, Message, Label>() {

        override suspend fun suspendExecuteIntent(intent: Intent, getState: () -> State) = when (intent) {
            Intent.Init -> init(getState = getState)
        }

        suspend fun init(getState: () -> State) {
            TODO("implement the init method")
        }
    }

""".trimIndent()
}


