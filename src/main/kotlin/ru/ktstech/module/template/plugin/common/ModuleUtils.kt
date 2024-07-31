package ru.ktstech.module.template.plugin.common

import com.android.tools.idea.wizard.template.ModuleTemplateData
import org.jetbrains.kotlin.konan.file.File


internal data class ModulePath(
    val root: String,
    val src: String,
)

internal fun ModuleTemplateData.getInnerModulePath(
    moduleName: String,
    innerModuleName: String,
): ModulePath {
    val rootPath = rootDir.absolutePath
        .replaceFirst(moduleName, "$moduleName${File.separator}$innerModuleName")

    val srcPath = srcDir.absolutePath
        .replace("java", "kotlin")
        .replaceFirst(moduleName, "$moduleName${File.separator}$innerModuleName")

    return ModulePath(
        src = srcPath,
        root = rootPath
    )
}

internal fun ModuleTemplateData.getCommonMainInnerModulePath(
    moduleName: String,
    innerModuleName: String,
): ModulePath {
    val innerModule = getInnerModulePath(
        moduleName = moduleName,
        innerModuleName = innerModuleName,
    )

    val commonMainSrcPath = innerModule.src.replace(
        "${File.separator}main${File.separator}",
        "${File.separator}commonMain${File.separator}"
    )

    return innerModule.copy(
        src = commonMainSrcPath,
    )
}

internal fun ModuleTemplateData.getModulePath(): ModulePath {
    return ModulePath(
        src = srcDir.absolutePath.replace("java", "kotlin"),
        root = rootDir.absolutePath
    )
}