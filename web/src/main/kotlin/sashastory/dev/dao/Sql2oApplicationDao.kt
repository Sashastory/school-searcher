package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import sashastory.dev.model.Application
import java.math.BigDecimal
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */
object Sql2oApplicationDao : Dao<Application> {

    fun getAllApplications(): List<Application> = findAll()

    fun getApplicationById(id: BigDecimal): Application = findById(id) ?: throw NotFoundException("Нет заявки с таким $id")

}