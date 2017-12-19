package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import sashastory.dev.model.Form
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */
object Sql2oFormDao : Dao<Form> {

    fun getAllForms(): List<Form> = findAll()

    fun getFormById(id: Long): Form = findById(id) ?: throw NotFoundException("Нет класса с таким $id")

}