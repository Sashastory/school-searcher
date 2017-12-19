package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.Position
import com.vaadin.shared.ui.ui.Transport
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import org.slf4j.LoggerFactory
import sashastory.dev.security.LoginService


@Theme("mytheme")
@Title("Подсистема поиска")
@Push(transport = Transport.WEBSOCKET_XHR)
class SecuredUI : UI() {

    override fun init(request: VaadinRequest?) {
        if (LoginService.currentUser == null) {
            content = LoginLayout()
            return
        }
        val content = valoMenu {
            appTitle = "<h3>Подсистема <strong>поиска и заявок</strong><h3>"
            section("")
            menuButton("Поиск по школам", VaadinIcons.BUILDING, view = SchoolSearchView::class.java)
            menuButton("Поиск по классам", VaadinIcons.CHILD, view = FormSearchView::class.java )
            section("")
            menuButton("Свободные места", VaadinIcons.ACADEMY_CAP, view = ApplicationView::class.java)
            section("")
            menuButton("О подсистеме", VaadinIcons.HOME, view = WelcomeView::class.java)
            menuButton("Выход", VaadinIcons.SIGN_OUT, block = { onLeftClick { LoginService.logout() } })
        }
        setContent(content)
        navigator = Navigator(this, content as ViewDisplay)
        navigator.addProvider(autoViewProvider)
        setErrorHandler { e ->
            log.error("Ошибка Vaadin UI ${e.throwable}", e.throwable)
            //Уведомление при ошибках
            Notification("Упс",
                    "Произошла ошибка :( Уже работаем над устранением!",
                    Notification.Type.ERROR_MESSAGE).apply {
                styleName = "${ValoTheme.NOTIFICATION_CLOSABLE} ${ValoTheme.NOTIFICATION_ERROR}"
                position = Position.TOP_CENTER
                show(Page.getCurrent())
            }
        }
    }

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(SecuredUI::class.java)
    }
}
