package ru.ktsstudio.module.template.plugin.common

import com.android.tools.idea.wizard.template.RecipeExecutor
import java.io.File

internal const val BUILD_GRADLE_FILE_NAME = "build.gradle.kts"

internal fun RecipeExecutor.saveFile(absolutePath: String, relative: String, content: String) {
    val path = absolutePath.replace("/java/", "/kotlin/")
    val file = File(path).resolve(relative)
    save(content, file)
}
