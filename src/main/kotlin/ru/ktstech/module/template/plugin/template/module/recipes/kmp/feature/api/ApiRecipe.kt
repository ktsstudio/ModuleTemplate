package ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.api

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.konan.file.File
import ru.ktstech.module.template.plugin.common.BUILD_GRADLE_FILE_NAME
import ru.ktstech.module.template.plugin.common.saveFile
import ru.ktstech.module.template.plugin.common.getInnerModulePath
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.api.domain.getMVIStoreTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.api.getApiBuildGradleTemplate

fun RecipeExecutor.createApi(
    moduleData: ModuleTemplateData,
    packageName: String,
    classPrefix: String,
    moduleName: String,
    withMvi: Boolean,
) {
    val (rootPath, srcPath) = moduleData.getInnerModulePath(
        moduleName = moduleName,
        innerModuleName = "api",
    )

    addIncludeToSettings(":shared:$moduleName:api")

    createApiBuildGradle(
        rootPath = rootPath,
        packageName = packageName,
    )

    if(withMvi) {
        createMVIApi(
            srcPath = srcPath,
            classPrefix = classPrefix,
            packageName = packageName,
        )
    }
}

private fun RecipeExecutor.createApiBuildGradle(
    rootPath: String,
    packageName: String,
) {
    saveFile(
        absolutePath = rootPath,
        relative = BUILD_GRADLE_FILE_NAME,
        content = getApiBuildGradleTemplate(
            packageName = packageName
        )
    )
}

private fun RecipeExecutor.createMVIApi(
    srcPath: String,
    packageName: String,
    classPrefix: String,
) {
    val apiDirDomain = "${srcPath}${File.separator}api${File.separator}domain"

    saveFile(
        absolutePath = apiDirDomain,
        relative = "${classPrefix}Store.kt",
        content = getMVIStoreTemplate(packageName = packageName, classPrefix = classPrefix),
    )
}
