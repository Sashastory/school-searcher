package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.server.Page
import com.vaadin.server.UserError
import com.vaadin.ui.Alignment
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.model.AppUser
import sashastory.dev.model.Sex
import sashastory.dev.service.RegistrationService

/**
 * @author Александр
 * @date 18.12.2017
 */
class RegistrationLayout : VerticalLayout(), View {

    private val binder = beanValidationBinder<AppUser>()

    init {
        label("Регистрация") {
            w = fillParent
            addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
        }

        formLayout {
            isMargin = false
            w = 800.px

            section("Личные данные")
            textField("Имя пользователя") {
                w = 50.perc
                bind(binder).trimmingConverter().bind(AppUser::userName)
            }
            passwordField("Пароль") {
                w = 50.perc
                bind(binder).bind(AppUser::password)
            }
            textField("Полное имя") {
                w = 50.perc
                bind(binder).bind(AppUser::fullName)
            }
            dateField("Дата рождения") {
                w = 50.perc
                bind(binder).bind(AppUser::dateOfBirth)
            }
            radioButtonGroup<Sex>("Пол") {
                styleName = ValoTheme.OPTIONGROUP_HORIZONTAL
                setItems(*Sex.values())
                bind(binder).bind(AppUser::sex)
            }
            section("Контактные данные")
            textField("Адрес электронной почты") {
                w = 50.perc
                bind(binder).bind(AppUser::email)
            }
            textField("Физичекий адрес") {
                w = 50.perc
                bind(binder).bind(AppUser::location)
            }
            textField("Номер телефона") {
                w = 50.perc
                bind(binder).bind(AppUser::phone)
            }
            section("")
            horizontalLayout {
                w = fillParent
                button("Назад") {
                    styleName = ValoTheme.BUTTON_ICON_ALIGN_RIGHT
                    icon = VaadinIcons.STEP_BACKWARD
                    alignment = Alignment.MIDDLE_LEFT
                    onLeftClick {
                        Page.getCurrent().reload()
                    }
                }
                button("Подтвердить") {
                    styleName = ValoTheme.BUTTON_ICON_ALIGN_RIGHT
                    icon = VaadinIcons.SIGN_IN
                    alignment = Alignment.MIDDLE_RIGHT
                    onLeftClick {
                        val user = AppUser()
                        // binder.validate() will highlight all invalid fields
                        if (binder.validate().hasErrors() || !binder.writeBeanIfValid(user)) {
                            componentError = UserError("Форма содержит некорректные поля")
                        } else {
                            componentError = null
                            this@RegistrationLayout.label("Сохраняем $user")
                            RegistrationService.register(user)
                            Page.getCurrent().reload()
                        }
                    }
                }
            }
        }
    }
 }

fun FormLayout.section(caption: String, style: String = ValoTheme.LABEL_H2, block: Label.()->Unit={}) = init(Label(caption)) {
    addStyleNames(style, ValoTheme.LABEL_COLORED)
    block()
}