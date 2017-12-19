package sashastory.dev

import com.github.vok.framework.VaadinOnKotlin
import com.github.vok.framework.sql2o.dataSource
import com.github.vok.framework.sql2o.dataSourceConfig
import com.github.vok.framework.sql2o.db
import com.microsoft.sqlserver.jdbc.SQLServerDriver
import com.vaadin.annotations.VaadinServletConfiguration
import com.vaadin.server.VaadinServlet
import org.flywaydb.core.Flyway
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
        log.info("Starting up")
        VaadinOnKotlin.dataSourceConfig.apply {
            driverClassName = SQLServerDriver::class.java.name
            jdbcUrl = "jdbc:sqlserver://SASHASTORY\\SQLEXPRESS;database=SCHOOL_SEARCHER"
            username = "sa"
            password = "A123456b"
        }
        log.info("Initializing VaadinOnKotlin")
        VaadinOnKotlin.init()
/*        log.info("Running DB migrations")
        val flyway = Flyway()
        flyway.dataSource = VaadinOnKotlin.dataSource
        flyway.migrate()*/
        log.info("Initialization complete")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        log.info("Shutting down");
        log.info("Destroying VaadinOnKotlin")
        VaadinOnKotlin.destroy()
        log.info("Shutdown complete")
    }

    companion object {
        private val log = LoggerFactory.getLogger(Bootstrap::class.java)

        init {
            // let java.util.logging log to slf4j
            SLF4JBridgeHandler.removeHandlersForRootLogger()
            SLF4JBridgeHandler.install()
        }
    }
}

@WebServlet(urlPatterns = arrayOf("/*"), name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SecuredUI::class, productionMode = false)
class MyUIServlet : VaadinServlet()

/**
 * RESTEasy configuration. Do not use Jersey, it has a tons of dependencies
 */
@ApplicationPath("/rest")
class ApplicationConfig : Application()
