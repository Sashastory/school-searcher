package sashastory.dev

import com.github.vok.framework.VaadinOnKotlin
import com.github.vok.framework.sql2o.dataSourceConfig
import com.microsoft.sqlserver.jdbc.SQLServerDriver
import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.server.VaadinServlet
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import sashastory.dev.ui.SecuredUI
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener
import javax.servlet.annotation.WebServlet
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application


@WebListener
class Bootstrap: ServletContextListener {
    override fun contextInitialized(sce: ServletContextEvent?) {
        VaadinOnKotlin.dataSourceConfig.apply {
            driverClassName = SQLServerDriver::class.java.name
            jdbcUrl = "jdbc:sqlserver://SASHASTORY\\SQLEXPRESS;database=SCHOOL_SEARCHER"
            username = "sa"
            password = "A123456b"
        }
        VaadinOnKotlin.init()
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        VaadinOnKotlin.destroy()
    }

    companion object {
        private val log = LoggerFactory.getLogger(Bootstrap::class.java)

        init {
            SLF4JBridgeHandler.removeHandlersForRootLogger()
            SLF4JBridgeHandler.install()
        }
    }
}

@WebServlet(urlPatterns = arrayOf("/*"), name = "SecuredUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SecuredUI::class, productionMode = true)
class SecuredUIServlet : VaadinServlet()

/**
 * RESTEasy конфигурация
 */
@ApplicationPath("/rest")
class ApplicationConfig : Application()
