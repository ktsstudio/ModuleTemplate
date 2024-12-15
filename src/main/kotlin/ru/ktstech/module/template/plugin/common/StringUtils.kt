package ru.ktstech.module.template.plugin.common

import java.util.*

internal fun String.appendIf(condition: Boolean): String = takeIf { condition }.orEmpty()

internal fun String.pascalCase(): String = lowercase()
    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }