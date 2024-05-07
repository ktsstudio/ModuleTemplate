package ru.ktstech.module.template.plugin.template.module.recipes.android.feature.compose

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.konan.file.File
import ru.ktstech.module.template.plugin.common.BUILD_GRADLE_FILE_NAME
import ru.ktstech.module.template.plugin.common.getModulePath
import ru.ktstech.module.template.plugin.common.saveFile
import ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.removeFiles
import ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.api.getFeatureApiScreenTemplate
import ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.getComposeBuildGradleTemplate
import ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.ui.getComposeScreenTemplate
import ru.ktstech.module.template.plugin.template.module.templates.android.feature.compose.ui.getRootComposeScreenTemplate

fun RecipeExecutor.createComposeFeatureModuleFiles(
    moduleData: ModuleTemplateData,
    classPrefix: String,
    withViewModel: Boolean,
) {
    val packageName = moduleData.packageName
    val corePackageName = packageName.split(".").subList(0, 2).joinToString(".") { it }

    moduleData.removeFiles()

    println(moduleData.projectTemplateData)

    val (rootPath, srcPath) = moduleData.getModulePath()

    createBuildGradle(rootPath = rootPath, packageName = packageName)

    createScreenApi(
        srcPath = srcPath,
        classPrefix = classPrefix,
        corePackageName = corePackageName,
        packageName = packageName,
    )

    createScreen(
        srcPath = srcPath,
        classPrefix = classPrefix,
        corePackageName = corePackageName,
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
    corePackageName: String,
) {

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
    corePackageName: String,
) {
    val dir = "${srcPath}${File.separator}ui"

    saveFile(
        absolutePath = dir,
        relative = "Root${classPrefix}Screen.kt",
        content = getRootComposeScreenTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            corePackageName = corePackageName,
            withViewModel = withViewModel,
        ),
    )

    saveFile(
        absolutePath = dir,
        relative = "${classPrefix}Screen.kt",
        content = getComposeScreenTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            corePackageName = corePackageName,
            withViewModel = withViewModel,
        ),
    )
}
