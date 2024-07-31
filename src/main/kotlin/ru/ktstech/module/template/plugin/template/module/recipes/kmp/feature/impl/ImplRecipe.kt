package ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.impl

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.konan.file.File
import ru.ktstech.module.template.plugin.common.BUILD_GRADLE_FILE_NAME
import ru.ktstech.module.template.plugin.common.saveFile
import ru.ktstech.module.template.plugin.common.getCommonMainInnerModulePath
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.getImplBuildGradleTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.data.getRepositoryImplTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.di.getImplKoinTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain.getMVIExecutorTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain.getMVIReducerTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain.getMVIStoreFactoryTemplate
import ru.ktstech.module.template.plugin.template.module.templates.kmp.feature.impl.src.domain.repository.getRepositoryTemplate

fun RecipeExecutor.createImpl(
    moduleData: ModuleTemplateData,
    packageName: String,
    classPrefix: String,
    moduleName: String,
    withMvi: Boolean,
    withRepository: Boolean,
) {
    val (rootPath, srcPath) = moduleData.getCommonMainInnerModulePath(
        moduleName = moduleName,
        innerModuleName = "impl",
    )

    addIncludeToSettings(":shared:$moduleName:impl")

    createImplBuildGradle(
        rootPath = rootPath,
        packageName = packageName,
    )

    createDiImpl(
        withMvi = withMvi,
        withRepository = withRepository,
        srcPath = srcPath,
        classPrefix = classPrefix,
        packageName = packageName,
    )

    if (withRepository) {
        createRepositoryImpl(
            srcPath = srcPath,
            classPrefix = classPrefix,
            packageName = packageName,
        )
    }

    if (withMvi) {
        createMVIImpl(
            srcPath = srcPath,
            classPrefix = classPrefix,
            packageName = packageName,
            withRepository = withRepository,
        )
    }
}

private fun RecipeExecutor.createImplBuildGradle(
    rootPath: String,
    packageName: String,
) {
    saveFile(
        absolutePath = rootPath,
        relative = BUILD_GRADLE_FILE_NAME,
        content = getImplBuildGradleTemplate(
            packageName = packageName
        )
    )
}

private fun RecipeExecutor.createDiImpl(
    srcPath: String,
    packageName: String,
    classPrefix: String,
    withMvi: Boolean,
    withRepository: Boolean
) {
    val implDirDi = "${srcPath}${File.separator}impl${File.separator}di"

    saveFile(
        absolutePath = implDirDi,
        relative = "Feature${classPrefix}ImplModule.kt",
        content = getImplKoinTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            withMvi = withMvi,
            withRepository = withRepository,
        ),
    )
}

private fun RecipeExecutor.createRepositoryImpl(
    srcPath: String,
    packageName: String,
    classPrefix: String,
) {
    val implDirDomain = "${srcPath}${File.separator}impl${File.separator}domain${File.separator}repository"
    val implDirData = "${srcPath}${File.separator}impl${File.separator}data${File.separator}"

    saveFile(
        absolutePath = implDirDomain,
        relative = "${classPrefix}Repository.kt",
        content = getRepositoryTemplate(packageName = packageName, classPrefix = classPrefix),
    )

    saveFile(
        absolutePath = implDirData,
        relative = "${classPrefix}RepositoryImpl.kt",
        content = getRepositoryImplTemplate(packageName = packageName, classPrefix = classPrefix),
    )
}


private fun RecipeExecutor.createMVIImpl(
    srcPath: String,
    packageName: String,
    classPrefix: String,
    withRepository: Boolean
) {
    val implDirDomain = "${srcPath}${File.separator}impl${File.separator}domain"

    saveFile(
        absolutePath = implDirDomain,
        relative = "${classPrefix}Executor.kt",
        content = getMVIExecutorTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            withRepository = withRepository
        ),
    )

    saveFile(
        absolutePath = implDirDomain,
        relative = "${classPrefix}Reducer.kt",
        content = getMVIReducerTemplate(packageName = packageName, classPrefix = classPrefix),
    )

    saveFile(
        absolutePath = implDirDomain,
        relative = "${classPrefix}StoreFactory.kt",
        content = getMVIStoreFactoryTemplate(
            packageName = packageName,
            classPrefix = classPrefix,
            withRepository = withRepository
        ),
    )
}
