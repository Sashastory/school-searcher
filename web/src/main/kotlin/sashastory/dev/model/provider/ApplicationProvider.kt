package sashastory.dev.model.provider

import com.github.vok.framework.sql2o.vaadin.EntityDataProvider
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.Application
import java.math.BigDecimal

/**
 * @author Александр
 * @date 18.12.2017
 */
object ApplicationProvider {

    private val data = EntityDataProvider(Application::class.java)

    fun getAllApplications(): List<Application> = data.getAll()

    fun getUserApplications(id: Long): List<Application>
            = data.and { Application::id eq BigDecimal(id) }.getAll()

    fun saveApplication(application: Application) {
        application.save()
    }
}