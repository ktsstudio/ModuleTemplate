package ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import org.jetbrains.kotlin.incremental.deleteRecursivelyOrThrow
import ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.api.createApi
import ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.impl.createImpl
import ru.ktstech.module.template.plugin.template.module.recipes.kmp.feature.presentation.createPresentation

fun RecipeExecutor.createKmpFeatureModuleFiles(
    moduleData: ModuleTemplateData,
    classPrefix: String,
    withRepository: Boolean,
    withMVI: Boolean,
    withApi: Boolean,
    withImpl: Boolean,
    withPresentation: Boolean,
) {
    val moduleName = moduleData.name.split(":").last()

    val packageName = moduleData.packageName

    moduleData.removeFiles()

    println(moduleData.projectTemplateData)

    if (withApi) {
        createApi(
            moduleData = moduleData,
            classPrefix = classPrefix,
            packageName = packageName,
            moduleName = moduleName,
            withMvi = withMVI,
        )
    }

    if (withImpl) {
        createImpl(
            moduleData = moduleData,
            classPrefix = classPrefix,
            packageName = packageName,
            moduleName = moduleName,
            withMvi = withMVI && withApi,
            withRepository = withRepository && withApi,
        )
    }

    if (withPresentation) {
        createPresentation(
            moduleData = moduleData,
            classPrefix = classPrefix,
            packageName = packageName,
            moduleName = moduleName,
            withMvi = withMVI && withImpl,
        )
    }
}

internal fun ModuleTemplateData.removeFiles() {
    resDir.deleteRecursivelyOrThrow()
    manifestDir.deleteRecursivelyOrThrow()
    rootDir.resolve("build.gradle").delete()
    rootDir.resolve("build.gradle.kts").delete()
    rootDir.resolve("proguard-rules.pro").delete()
    rootDir.resolve("libs").delete()
    rootDir.deleteRecursivelyOrThrow()
}