package ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.ui

import ru.ktstech.module.template.plugin.common.appendIf

fun getRootComposeScreenTemplate(
    packageName: String,
    corePackageName: String,
    classPrefix: String,
    withViewModel: Boolean,
): String {
    return """
    package ${packageName}.ui

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    ${"import org.koin.androidx.compose.koinViewModel".appendIf(withViewModel)}
    ${"import ${packageName}.presentation.${classPrefix}ViewModel".appendIf(withViewModel)}
    ${"import ${packageName}.presentation.models.Ui${classPrefix}Label".appendIf(withViewModel)}
    ${"import ${packageName}.presentation.models.Ui${classPrefix}State".appendIf(withViewModel)}
    ${"import ${corePackageName}.mobile.common_ui.common.view.utils.toast".appendIf(withViewModel)}

    @Composable
    internal fun Root${classPrefix}Screen( 
        onBackClick: () -> Unit,
        ${"viewModel: ${classPrefix}ViewModel = koinViewModel(),".appendIf(withViewModel)}
    ) {
        ${
        """
         val state by viewModel.state.collectAsStateWithLifecycle()
         val context = LocalContext.current
        """.trimIndent().appendIf(withViewModel)
        }
       
        ${classPrefix}Screen(${"state = state".appendIf(withViewModel)})
        
        ${
        """
         LaunchedEffect(key1 = true) {
            viewModel.label.collect { label ->
                when (label) {
                    is Ui${classPrefix}Label.Message -> context.toast(label.messageRes.resourceId)
                }
            }
         }
        """.trimIndent().appendIf(withViewModel)
         } 
    }
""".trimIndent()
}