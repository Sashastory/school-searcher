package sashastory.dev.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import com.github.vok.framework.sql2o.findById
import sashastory.dev.dao.Sql2oAppUserDao
import sashastory.dev.dao.Sql2oFormDao
import sashastory.dev.dao.Sql2oSchoolDao
import java.io.Serializable
import java.util.*

/**
 * @author Александр
 * @date 18.12.2017
 */
@Table("APPLICATION")
data class Application(

        override var id: Long? = null,

        var appUserId: Long? = null,

        var schoolId: Long? = null,

        var formId: Long? = null,

        var applicationDate: Date? = null

) : Entity<Long>, Serializable {

    @get:JsonIgnore
    val appUser: AppUser?
        get() = if (appUserId == null) null else Sql2oAppUserDao.findById(id!!)

    @get:JsonIgnore
    val school: School?
        get() = if (schoolId == null) null else Sql2oSchoolDao.findById(id!!)

    @get:JsonIgnore
    val form: Form?
        get() = if (formId == null) null else Sql2oFormDao.findById(id!!)
}