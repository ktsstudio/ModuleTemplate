package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src

fun getViewModelTemplate(
    corePackageName: String,
    packageName: String,
    classPrefix: String,
    withMvi: Boolean,
): String {
    return when {
        withMvi -> """
            package ${packageName}.presentation
         
            import com.arkivanov.mvikotlin.extensions.coroutines.labels
            import com.arkivanov.mvikotlin.extensions.coroutines.states
            import kotlinx.coroutines.flow.mapNotNull
            import ${packageName}.api.domain.${classPrefix}Store 
            import ${packageName}.presentation.mappers.Ui${classPrefix}LabelMapper
            import ${packageName}.presentation.mappers.Ui${classPrefix}StateMapper
            import ${packageName}.presentation.models.Ui${classPrefix}Label
            import ${packageName}.presentation.models.Ui${classPrefix}State
            import ${corePackageName}.core.presentation.common.BaseViewModel
            
            class ${classPrefix}ViewModel(
                private val store: ${classPrefix}Store, 
                private val stateMapper: Ui${classPrefix}StateMapper,
                private val labelMapper: Ui${classPrefix}LabelMapper,
            ) : BaseViewModel<Ui${classPrefix}State, Ui${classPrefix}Label>(Ui${classPrefix}State()) {
            
                init {
                    bindAndStart {
                        store.states.mapNotNull(stateMapper::map).bindTo(::acceptState)
                        store.labels.mapNotNull(labelMapper::map).bindTo(::acceptLabel)
                    }     
                       
                    store.accept(${classPrefix}Store.Intent.Init)
                }
                
                fun refresh() {
                    store.accept(${classPrefix}Store.Intent.Refresh)
                }
            }
            """.trimIndent()

        else -> """
            package ${packageName}.presentation
         
            import kotlinx.coroutines.flow.mapNotNull 
            import ${packageName}.presentation.mappers.Ui${classPrefix}LabelMapper
            import ${packageName}.presentation.mappers.Ui${classPrefix}StateMapper
            import ${packageName}.presentation.models.Ui${classPrefix}Label
            import ${packageName}.presentation.models.Ui${classPrefix}State
            import ${corePackageName}.core.presentation.common.BaseViewModel
            
            class ${classPrefix}ViewModel( 
                private val stateMapper: Ui${classPrefix}StateMapper,
                private val labelMapper: Ui${classPrefix}LabelMapper,
            ) : BaseViewModel<Ui${classPrefix}State, Ui${classPrefix}Label>(Ui${classPrefix}State()) {
            
            }
            """.trimIndent()
    }
}


