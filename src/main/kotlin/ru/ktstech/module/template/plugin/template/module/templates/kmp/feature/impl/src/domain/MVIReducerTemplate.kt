package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain

fun getMVIReducerTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package $packageName.impl.domain
 
    import com.arkivanov.mvikotlin.core.store.Reducer 
    import ${packageName}.api.domain.${classPrefix}Store.State
    import ${packageName}.impl.domain.${classPrefix}StoreFactory.Message 
    
    internal class ${classPrefix}Reducer : Reducer<State, Message> {
    
        override fun State.reduce(msg: Message): State = when (msg) {
               is Message.SetLoading -> copy(loading = msg.loading, error = null)
               is Message.Error -> copy(loading = false, error = msg.throwable)
        }
    }
""".trimIndent()
}

