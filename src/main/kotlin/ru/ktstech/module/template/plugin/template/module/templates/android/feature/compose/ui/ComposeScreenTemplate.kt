package ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.ui

import ru.ktstech.module.template.plugin.common.appendIf

fun getComposeScreenTemplate(
    packageName: String,
    corePackageName: String,
    classPrefix: String,
    withViewModel: Boolean,
): String {
    return """
    package $packageName.ui

    import androidx.compose.runtime.Composable 
    import androidx.compose.foundation.layout.WindowInsets 
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Scaffold
    import androidx.compose.ui.Modifier 
    import androidx.compose.ui.unit.dp  
    ${"import ${corePackageName}.mobile.common_ui.common.compose.state.ShowUiState".appendIf(withViewModel)}
    ${"import ${packageName}.presentation.models.Ui${classPrefix}State".appendIf(withViewModel)}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ${classPrefix}Screen(
        ${"state: Ui${classPrefix}State,".appendIf(withViewModel)}
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            modifier = modifier,
            contentWindowInsets = WindowInsets(bottom = 0.dp),
        ) { contentPadding ->
            ${"// TODO ${classPrefix.uppercase()} IMPLEMENT".appendIf(withViewModel.not())}
            ${
            """
             ShowUiState(state = state) { details ->
                // TODO ${classPrefix.uppercase()} IMPLEMENT
             }
            """.trimIndent().appendIf(withViewModel)
            } 
        }
    }
""".trimIndent()
}