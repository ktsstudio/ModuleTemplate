package ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.api

fun getFeatureApiScreenTemplate(
    corePackageName: String,
    packageName: String,
    classPrefix: String,
): String {
    return """
        package $packageName.api
     
        import androidx.compose.animation.ExperimentalAnimationApi
        import androidx.navigation.NavController
        import androidx.navigation.NavGraphBuilder
        import androidx.navigation.compose.composable
        import $packageName.ui.Root${classPrefix}Screen 
        import $corePackageName.mobile.navigation.navigateOnResumed
        
        fun NavController.navigateToReservationCreate() {
            navigateOnResumed(
                route = "$classPrefix", // TODO ${classPrefix.uppercase()}: replace to Screen.${classPrefix}.route
            ) {
                launchSingleTop = true
            }
        }
         
        fun NavGraphBuilder.composableReservationCreateScreen( 
            onBack: () -> Unit,
        ) {
            composable(
                route = "$classPrefix", // TODO ${classPrefix.uppercase()}: replace to Screen.${classPrefix}.route
            ) { navBackStack -> 
                Root${classPrefix}Screen( 
                    onBackClick = onBack, 
                )
            }
        }
    """.trimIndent()
}


