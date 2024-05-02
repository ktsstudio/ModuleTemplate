package ru.ktsstudio.module.template.plugin.template.module.recipes.android.feature.compose

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.konan.file.File
import ru.ktsstudio.module.template.plugin.common.BUILD_GRADLE_FILE_NAME
import ru.ktsstudio.module.template.plugin.common.getModulePath
import ru.ktsstudio.module.template.plugin.common.saveFile
import ru.ktsstudio.module.template.plugin.template.module.recipes.kmp.feature.removeFiles
import ru.ktsstudio.module.template.plugin.template.module.templates.android.feature.compose.api.getFeatureApiScreenTemplate
import ru.ktsstudio.module.template.plugin.template.module.templates.android.feature.compose.getComposeBuildGradleTemplate
import ru.ktsstudio.module.template.plugin.template.module.templates.android.feature.compose.ui.getComposeScreenTemplate

fun RecipeExecutor.createComposeFeatureModuleFiles(
    moduleData: ModuleTemplateData,
    classPrefix: String,
    withViewModel: Boolean,
) {
    val packageName = moduleData.packageName

    moduleData.removeFiles()

    println(moduleData.projectTemplateData)

    val (rootPath, srcPath) = moduleData.getModulePath()

    createBuildGradle(rootPath = rootPath, packageName = packageName)

    createScreenApi(
        srcPath = srcPath,
        classPrefix = classPrefix,
        packageName = packageName,
    )

    createScreen(
        srcPath = srcPath,
        classPrefix = classPrefix,
        packageName = packageName,
        withViewModel = withViewModel,
    )
}


private fun RecipeExecutor.createBuildGradle(
    rootPath: String,
    packageName: String,
) {
    saveFile(
        absolutePath = rootPath,
        relative = BUILD_GRADLE_FILE_NAME,
        content = getComposeBuildGradleTemplate(
            packageName = packageName
        )
    )
}

private fun RecipeExecutor.createScreenApi(
    srcPath: String,
    packageName: String,
    classPrefix: String,
) {
    val corePackageName = packageName.split(".").subList(0, 2).joinToString(".") { it }

    val dir = "${srcPath}${File.separator}api"

    saveFile(
        absolutePath = dir,
        relative = "Feature${classPrefix}ScreenApi.kt",
        content = getFeatureApiScreenTemplate(
            corePackageName = corePackageName,
            packageName = packageName,
            classPrefix = classPrefix
        ),
    )
}

private fun RecipeExecutor.createScreen(
    srcPath: String,
    packageName: String,
    classPrefix: String,
    withViewModel: Boolean,
) {
    val dir = "${srcPath}${File.separator}ui"

    saveFile(
        absolutePath = dir,
        relative = "${classPrefix}Screen.kt",
        content = getComposeScreenTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            withViewModel = withViewModel,
        ),
    )
}
