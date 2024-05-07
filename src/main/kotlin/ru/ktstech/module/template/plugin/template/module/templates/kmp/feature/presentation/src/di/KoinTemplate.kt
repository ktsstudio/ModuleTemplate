package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.di

import ru.ktstech.module.template.plugin.common.appendIf

fun getPresentationKoinTemplate(
    packageName: String,
    classPrefix: String,
    withMvi: Boolean,
): String {
    return """
    package $packageName.presentation.di
     
    import org.koin.dsl.module
    import $packageName.presentation.mappers.Ui${classPrefix}LabelMapper
    import $packageName.presentation.mappers.Ui${classPrefix}StateMapper
    import $packageName.presentation.mappers.Ui${classPrefix}LabelMapperImpl
    import $packageName.presentation.mappers.Ui${classPrefix}StateMapperImpl
    import $packageName.presentation.${classPrefix}ViewModel
   
    val feature${classPrefix}PresentationModule = module {
  
        factory<Ui${classPrefix}StateMapper> {
            Ui${classPrefix}StateMapperImpl()
        }
        
        factory<Ui${classPrefix}LabelMapper> {
            Ui${classPrefix}LabelMapperImpl()
        }
        
        factory {
            ${classPrefix}ViewModel(
                ${"store = get(),".appendIf(withMvi)}
                stateMapper = get(),
                labelMapper = get(),
            ) 
        } 
    }
    """.trimIndent()
}