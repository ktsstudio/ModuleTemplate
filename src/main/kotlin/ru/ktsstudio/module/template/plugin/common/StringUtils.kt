package ru.ktsstudio.module.template.plugin.common

internal fun String.appendIf(condition: Boolean): String = takeIf { condition }.orEmpty()