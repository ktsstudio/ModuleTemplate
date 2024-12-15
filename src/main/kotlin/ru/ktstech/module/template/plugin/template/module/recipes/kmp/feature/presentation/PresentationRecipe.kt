package ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.presentation

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.konan.file.File
import ru.ktstech.module.template.plugin.common.BUILD_GRADLE_FILE_NAME
import ru.ktstech.module.template.plugin.common.saveFile
import ru.ktstech.module.template.plugin.common.getInnerModulePath
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.getPresentationBuildGradleTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.di.getPresentationKoinTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.getViewModelTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.mappers.getUiLabelMapperTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.mappers.getUiStateMapperTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.models.getUiLabelModelTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.presentation.src.models.getUiStateModelTemplate

fun RecipeExecutor.createPresentation(
    moduleData: ModuleTemplateData,
    packageName: String,
    classPrefix: String,
    moduleName: String,
    withMvi: Boolean,
) {
    val corePackageName = packageName.split(".").subList(0, 2).joinToString(".") { it }

    val (rootPath, srcPath) = moduleData.getInnerModulePath(
        moduleName = moduleName,
        innerModuleName = "presentation",
    )

    addIncludeToSettings(":shared:$moduleName:presentation")

    createPresentationBuildGradle(
        rootPath = rootPath,
        packageName = packageName,
    )

    createDiPresentation(
        srcPath = srcPath,
        classPrefix = classPrefix,
        packageName = packageName,
        withMvi = withMvi,
    )

    createViewModelPresentation(
        srcPath = srcPath,
        packageName = packageName,
        corePackageName = corePackageName,
        classPrefix = classPrefix,
        withMvi = withMvi,
    )

    createModelsPresentation(
        srcPath = srcPath,
        packageName = packageName,
        corePackageName = corePackageName,
        classPrefix = classPrefix,
    )

    createMappersPresentation(
        srcPath = srcPath,
        packageName = packageName,
        classPrefix = classPrefix,
        withMvi = withMvi,
    )
}

private fun RecipeExecutor.createMappersPresentation(
    srcPath: String,
    packageName: String,
    classPrefix: String,
    withMvi: Boolean
) {
    if (withMvi.not()) return

    val dirMappers = "${srcPath}${File.separator}presentation${File.separator}mappers"

    saveFile(
        absolutePath = dirMappers,
        relative = "Ui${classPrefix}StateMapper.kt",
        content = getUiStateMapperTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
        ),
    )

    saveFile(
        absolutePath = dirMappers,
        relative = "Ui${classPrefix}LabelMapper.kt",
        content = getUiLabelMapperTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
        ),
    )
}

private fun RecipeExecutor.createModelsPresentation(
    srcPath: String,
    packageName: String,
    corePackageName: String,
    classPrefix: String,
) {
    val dirModels = "${srcPath}${File.separator}presentation${File.separator}models"

    saveFile(
        absolutePath = dirModels,
        relative = "Ui${classPrefix}State.kt",
        content = getUiStateModelTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            corePackageName = corePackageName,
        ),
    )

    saveFile(
        absolutePath = dirModels,
        relative = "Ui${classPrefix}Label.kt",
        content = getUiLabelModelTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
        ),
    )
}

private fun RecipeExecutor.createViewModelPresentation(
    srcPath: String,
    packageName: String,
    corePackageName: String,
    classPrefix: String,
    withMvi: Boolean
) {

    saveFile(
        absolutePath = "$srcPath${File.separator}presentation",
        relative = "${classPrefix}ViewModel.kt",
        content = getViewModelTemplate(
            corePackageName = corePackageName,
            packageName = packageName,
            classPrefix = classPrefix,
            withMvi = withMvi,
        ),
    )
}

private fun RecipeExecutor.createDiPresentation(
    srcPath: String,
    packageName: String,
    classPrefix: String,
    withMvi: Boolean
) {
    val dirDi = "${srcPath}${File.separator}presentation${File.separator}di"

    saveFile(
        absolutePath = dirDi,
        relative = "Feature${classPrefix}PresentationModule.kt",
        content = getPresentationKoinTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            withMvi = withMvi
        ),
    )
}

private fun RecipeExecutor.createPresentationBuildGradle(
    rootPath: String,
    packageName: String,
) {
    saveFile(
        absolutePath = rootPath,
        relative = BUILD_GRADLE_FILE_NAME,
        content = getPresentationBuildGradleTemplate(
            packageName = packageName
        )
    )
}