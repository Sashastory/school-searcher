package sashastory.dev.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Filter
import com.github.vok.framework.sql2o.Table
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.vaadin.data.provider.DataProvider
import sashastory.dev.dao.Sql2oFormDao
import java.io.Serializable
import java.time.LocalDate
import java.util.*

/**
 * @author Александр
 * @date 18.12.2017
 */
@Table("SCHOOL")
data class School(

        override var id: Long? = null,

        var schoolName: String? = null,

        var principal: String? = null,

        var schoolAddress: String? = null,

        var phone: String? = null,

        var foundationDate: LocalDate? = null

) : Entity<Long>, Serializable {

    @get:JsonIgnore
    val forms: DataProvider<Form, Filter<Form>?>
        get() = Sql2oFormDao.dataProvider.and { Form::schoolId eq id }
}