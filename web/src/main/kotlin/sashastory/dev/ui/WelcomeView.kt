package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.ThemeResource
import com.vaadin.shared.Version
import com.vaadin.ui.AbstractOrderedLayout
import com.vaadin.ui.Alignment
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView("")
class WelcomeView : VerticalLayout(), View {
    init {
        verticalLayout {
            setSizeFull()
            isMargin = false
            label("Добро пожаловать в подсистему!") {
                alignment = Alignment.TOP_CENTER
                addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
            }
            image(resource = ThemeResource("images/students.jpeg")) {
                alignment = Alignment.MIDDLE_CENTER
            }
            label { html("<strong>Версия Vaadin: </strong> ${Version.getFullVersion()}") }
            label { html("<strong>Версия Kotlin: </strong> ${KotlinVersion.CURRENT}") }
            label { html("<strong>Версия JVM:</strong> $jvmVersion") }
        }
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
