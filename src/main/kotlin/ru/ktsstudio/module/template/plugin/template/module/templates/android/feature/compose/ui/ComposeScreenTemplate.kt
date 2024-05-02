package ru.ktsstudio.module.template.plugin.template.module.templates.android.feature.compose.ui

import ru.ktsstudio.module.template.plugin.common.appendIf

fun getComposeScreenTemplate(
    packageName: String,
    classPrefix: String,
    withViewModel: Boolean,
): String {
    return """
    package $packageName.ui

    import androidx.compose.runtime.Composable
    ${"import org.koin.androidx.compose.koinViewModel".appendIf(withViewModel)}
    ${"import $packageName.presentation.${classPrefix}ViewModel".appendIf(withViewModel)}

    @Composable
    internal fun Root${classPrefix}Screen( 
        onBackClick: () -> Unit,
        ${"viewModel: ${classPrefix}ViewModel = koinViewModel(),".appendIf(withViewModel)}
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        
        // todo impl
    }
""".trimIndent()
}


