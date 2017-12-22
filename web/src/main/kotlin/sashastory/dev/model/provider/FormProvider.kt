package sashastory.dev.model.provider

import com.github.vok.framework.sql2o.vaadin.EntityDataProvider
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.Form

/**
 * @author Александр
 * @date 18.12.2017
 */
object FormProvider {

    private val data = EntityDataProvider(Form::class.java)

    fun getAllForms(): List<Form> = data.getAll()

    fun getFormsByNumber(number: String): List<Form> =
            data.and { Form::formNumber like number }.getAll()


    fun getFormsByTeacherName(name: String): List<Form> =
            data.and { Form::teacherName like name }.getAll()


    fun getFormsByStudentAmount(amount: Int): List<Form> =
            data.and { Form::studentAmount eq amount }.getAll()

    fun getFormsWithSpotsForSchoolId(id: Long, amount: Int = 30): List<Form> =
            data.and { Form::schoolId eq id }
                    .and { Form::studentAmount lt amount }
                    .getAll()
}