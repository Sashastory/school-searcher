package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.Form
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */
object Sql2oFormDao : Dao<Form> {

    fun getAllForms(): List<Form> = findAll()

    fun getFormById(id: Long): Form = findById(id) ?: throw NotFoundException("Нет класса с таким $id")

    fun getFormsByNumber(number: String): List<Form> =
            Sql2oFormDao.dataProvider.and { Form::formNumber like number }.getAll()


    fun getFormsByTeacherName(name: String): List<Form> =
            Sql2oFormDao.dataProvider.and { Form::teacherName like name }.getAll()


    fun getFormsByStudentAmount(amount: Int): List<Form> =
            Sql2oFormDao.dataProvider.and { Form::studentAmount eq amount }.getAll()


    fun getFormsForSchoolId(id: Long): List<Form> =
            Sql2oFormDao.dataProvider.and { Form::schoolId eq id }.getAll()

    fun getFormsWithSpotsForSchoolId(id: Long, amount: Int = 30): List<Form> =
            Sql2oFormDao.dataProvider.and { Form::schoolId eq id }
                    .and { Form::studentAmount lt amount }
                    .getAll()
}