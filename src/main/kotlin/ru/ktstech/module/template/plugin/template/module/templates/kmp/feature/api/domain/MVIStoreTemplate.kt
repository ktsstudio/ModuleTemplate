package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.api.domain

fun getMVIStoreTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package $packageName.api.domain

    import com.arkivanov.mvikotlin.core.store.Store
    import dev.icerock.moko.resources.StringResource
    import ${packageName}.api.domain.${classPrefix}Store.Intent
    import ${packageName}.api.domain.${classPrefix}Store.Label
    import ${packageName}.api.domain.${classPrefix}Store.State 

    interface ${classPrefix}Store : Store<Intent, State, Label> {
    
        data class State(
             val loading: Boolean,
             val error: Throwable?,
        ) {
            companion object {
                val Default = State(
                    loading = false,
                    error = null,
                )
            }
        }
    
        sealed interface Intent {
            object Init : Intent
            object Refresh : Intent
        }
    
        sealed interface Label {
            data class Message(val message: StringResource) : Label
        }
    }
""".trimIndent()
}


