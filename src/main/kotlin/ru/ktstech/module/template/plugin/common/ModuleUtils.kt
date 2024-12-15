package ru.ktstech.module.template.plugin.common

import com.android.tools.idea.wizard.template.ModuleTemplateData


internal data class ModulePath(
    val root: String,
    val src: String,
)

internal fun ModuleTemplateData.getInnerModulePath(
    moduleName: String,
    innerModuleName: String,
): ModulePath {
    val rootPath = rootDir.absolutePath
        .replaceFirst(moduleName, "$moduleName${org.jetbrains.kotlin.konan.file.File.separator}$innerModuleName")

    val srcPath = srcDir.absolutePath
        .replace("java", "kotlin")
        .replaceFirst(moduleName, "$moduleName${org.jetbrains.kotlin.konan.file.File.separator}$innerModuleName")

    return ModulePath(
        src = srcPath,
        root = rootPath
    )
}

internal fun ModuleTemplateData.getModulePath(): ModulePath {
    return ModulePath(
        src = srcDir.absolutePath.replace("java", "kotlin"),
        root = rootDir.absolutePath
    )
}