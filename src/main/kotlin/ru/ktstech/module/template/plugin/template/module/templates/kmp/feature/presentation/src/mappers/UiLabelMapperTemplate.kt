package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.mappers

fun getUiLabelMapperTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package ${packageName}.presentation.mappers
   
    import ${packageName}.api.domain.${classPrefix}Store.Label
    import ${packageName}.presentation.models.Ui${classPrefix}Label
    import ru.ktsstudio.kotlin_utils.mapper.Mapper
    
    interface Ui${classPrefix}LabelMapper : Mapper<Label, Ui${classPrefix}Label>
    
    internal class Ui${classPrefix}LabelMapperImpl : Ui${classPrefix}LabelMapper {
    
        override fun map(item: Label): Ui${classPrefix}Label = when (item) {
            is Label.Message -> Ui${classPrefix}Label.Message(item.message)
        }
    }
    """.trimIndent()
}


