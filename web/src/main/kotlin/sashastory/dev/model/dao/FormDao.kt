package sashastory.dev.model.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.Filter
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.vaadin.data.provider.DataProvider
import sashastory.dev.model.Form
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */
object FormDao : Dao<Form> {

    private val data: DataProvider<Form, Filter<Form>?> = dataProvider

    fun getAllForms(): List<Form> = findAll()

    fun getFormById(id: Long): Form = findById(id) ?: throw NotFoundException("Нет класса с таким $id")

    fun getFormsByNumber(number: String): List<Form> =
            data.and { Form::formNumber like number }.getAll()


    fun getFormsByTeacherName(name: String): List<Form> =
            data.and { Form::teacherName like name }.getAll()


    fun getFormsByStudentAmount(amount: Int): List<Form> =
            data.and { Form::studentAmount eq amount }.getAll()


    fun getFormsForSchoolId(id: Long): List<Form> =
            data.and { Form::schoolId eq id }.getAll()

    fun getFormsWithSpotsForSchoolId(id: Long, amount: Int = 30): List<Form> =
            data.and { Form::schoolId eq id }
                    .and { Form::studentAmount lt amount }
                    .getAll()
}