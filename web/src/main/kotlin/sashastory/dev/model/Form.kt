package sashastory.dev.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import com.github.vok.framework.sql2o.findById
import sashastory.dev.dao.Sql2oSchoolDao
import java.io.Serializable
import javax.validation.constraints.Max

/**
 * @author Александр
 * @date 18.12.2017
 */

@Table("FORM")
data class Form(
        override var id: Long? = null,

        var schoolId: Long? = null,

        val formNumber: String? = null,

        val teacherName: String? = null,

        @Max(30)
        val studentAmount: Int? = null

) : Entity<Long>, Serializable {

    @get:JsonIgnore
    val school: School?
        get() = if (schoolId == null) null else Sql2oSchoolDao.findById(schoolId!!)
}