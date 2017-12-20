package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.ui.Alignment
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.security.Authentication

/**
 * @author Александр
 * @date 17.12.2017
 */
class LoginLayout : VerticalLayout() {
    private lateinit var username : TextField
    private lateinit var password: TextField

    init {
        setSizeFull()
        label("Подсистема поиска школ и заявок на поступление") {
            addStyleNames(ValoTheme.LABEL_COLORED, ValoTheme.LABEL_H1)
            alignment = Alignment.TOP_CENTER
        }
        panel {
            w = 500.px
            alignment = Alignment.MIDDLE_CENTER

            verticalLayout {
                w = fillParent
                formLayout {
                    w = fillParent
                    username = textField("Имя пользователя") {
                        w = fillParent
                    }
                    password = passwordField("Пароль") {
                        w = fillParent
                    }
                }
                horizontalLayout {
                    w = fillParent
                    button("Регистрация") {
                        alignment = Alignment.MIDDLE_LEFT
                        styleName = ValoTheme.BUTTON_BORDERLESS_COLORED
                        onLeftClick { UI.getCurrent().content = RegistrationLayout() }
                    }
                    button("Вход") {
                        alignment = Alignment.MIDDLE_RIGHT
                        setPrimary()
                        onLeftClick { login() }
                    }

                }
            }

        }

    }

    private fun login() {
        Authentication.login(username.value, password.value)
    }
}