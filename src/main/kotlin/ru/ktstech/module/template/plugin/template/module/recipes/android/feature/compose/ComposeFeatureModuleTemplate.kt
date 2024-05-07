package ru.ktstech.module.template.plugin.template.module.recipes.android.feature.compose

import com.android.tools.idea.wizard.template.*
import ru.ktstech.module.template.plugin.common.pascalCase

val composeFeatureModuleTemplate
    get() = template {
        name = "Compose Feature Module"
        minApi = 21
        description = "Compose feature module for android"

        category = Category.Folder
        formFactor = FormFactor.Mobile
        useGenericAndroidTests = false
        useGenericLocalTests = false

        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule,
        )

        val classPrefix = stringParameter {
            name = "Введите префикс для классов модуля"
            default = ""
        }

        val withViewModel = booleanParameter {
            name = "Добавить ViewModel"
            default = true
        }

        widgets(
            TextFieldWidget(classPrefix),
            Separator,
            CheckBoxWidget(withViewModel),
        )

        recipe = { data: TemplateData ->
            createComposeFeatureModuleFiles(
                moduleData = data as ModuleTemplateData,
                classPrefix = classPrefix.value.pascalCase(),
                withViewModel = withViewModel.value,
            )
        }
    }

