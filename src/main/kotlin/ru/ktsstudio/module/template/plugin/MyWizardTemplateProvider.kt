package ru.ktsstudio.module.template.plugin

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import ru.ktsstudio.module.template.plugin.template.module.recipes.android.feature.compose.composeFeatureModuleTemplate
import ru.ktsstudio.module.template.plugin.template.module.recipes.kmp.feature.kmpFeatureModuleTemplate

class MyWizardTemplateProvider : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> {
        return listOf(
            kmpFeatureModuleTemplate,
            composeFeatureModuleTemplate
        )
    }
}
