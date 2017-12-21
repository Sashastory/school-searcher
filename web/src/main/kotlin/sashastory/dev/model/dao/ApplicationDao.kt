package sashastory.dev.model.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.Filter
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.vaadin.data.provider.DataProvider
import sashastory.dev.model.Application
import java.math.BigDecimal
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */
object ApplicationDao : Dao<Application> {

    private val data: DataProvider<Application, Filter<Application>?> = dataProvider

    fun getAllApplications(): List<Application> = findAll()

    fun getApplicationById(id: Long): Application
            = findById(BigDecimal(id)) ?: throw NotFoundException("Нет заявки с таким $id")

    fun getUserApplications(id: Long): List<Application>
            = data.and { Application::id eq BigDecimal(id) }.getAll()

}