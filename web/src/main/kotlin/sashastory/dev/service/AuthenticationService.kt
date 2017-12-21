package sashastory.dev.service

import com.github.vok.framework.Session
import com.vaadin.server.Page
import com.vaadin.server.VaadinSession
import com.vaadin.shared.Position
import com.vaadin.ui.Notification
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.model.AppUser

/**
 * @author Александр
 * @date 17.12.2017
 */

object AuthenticationService {

    val currentUser: AppUser?
        get() = Session[AppUser::class]

    fun login(userName:String, password: String) {

        val dbUser = DataService.appUserDao.getUserByUserNameAndPassword(userName, password).firstOrNull {
            user -> user.userName == userName && user.password == password
        }

        if (dbUser == null) {
            Notification("Эх", "Нет пользователя с такими данными!",
                    Notification.Type.ERROR_MESSAGE).apply {
                styleName = "${ValoTheme.NOTIFICATION_CLOSABLE} ${ValoTheme.NOTIFICATION_ERROR}"
                position = Position.MIDDLE_CENTER
                show(Page.getCurrent())
            }
            return
        }
        Session[AppUser::class] = dbUser
        Page.getCurrent().reload()
    }

    fun logout() {
        VaadinSession.getCurrent().close()
        Page.getCurrent().reload()
    }
}
