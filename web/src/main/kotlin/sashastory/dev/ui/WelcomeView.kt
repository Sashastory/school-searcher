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
        setSizeFull()
        isMargin = false
        label("Добро пожаловать в подсистему!") {
            alignment = Alignment.TOP_CENTER
            addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
        }
        panel("Функции") {
            verticalLayout {
                setSizeFull()
                isMargin = false
                defaultComponentAlignment = Alignment.TOP_LEFT
                label("- Поиск школ по их параметрам") {
                    styleName = ValoTheme.LABEL_LARGE
                }
                label ("- Поиск классов по их параметрам") {
                    styleName = ValoTheme.LABEL_LARGE
                }
                label("- Заявка на поступление при наличии свободных мест в школе") {
                    styleName = ValoTheme.LABEL_LARGE
                }
            }
        }
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
