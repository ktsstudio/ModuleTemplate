package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain

import ru.ktstech.module.template.plugin.common.appendIf

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
            override suspend fun suspendExecuteIntent(intent: Intent) {
                when (intent) {
                    Intent.Init -> init()
                    Intent.Refresh -> refresh()
                }
            }

            private suspend fun init() {
                TODO("implement the init method")
            }
        
            private suspend fun refresh() {
                TODO("implement the init method")
            }
    }
""".trimIndent()
}


