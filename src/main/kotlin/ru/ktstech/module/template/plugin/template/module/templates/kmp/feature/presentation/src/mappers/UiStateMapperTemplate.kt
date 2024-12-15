package ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.mappers

fun getUiStateMapperTemplate(
    packageName: String,
    classPrefix: String,
): String {
    return """
    package ${packageName}.presentation.mappers
 
    import ${packageName}.api.domain.${classPrefix}Store.State 
    import ${packageName}.presentation.models.Ui${classPrefix}State
    import ${packageName}.presentation.models.Ui${classPrefix}Details
    import ru.ktsstudio.kotlin_utils.mapper.Mapper
    
    interface Ui${classPrefix}StateMapper : Mapper<State, Ui${classPrefix}State>
    
    internal class Ui${classPrefix}StateMapperImpl : Ui${classPrefix}StateMapper {
    
        override fun map(item: State): Ui${classPrefix}State = with(item) {
            Ui${classPrefix}State(
                loading = loading,
                error = error != null, 
                details = Ui${classPrefix}Details(
                    anyParams = null, // TODO ${classPrefix.uppercase()} IMPLEMENT
                ),
            )
        }
    }
    """.trimIndent()
}


