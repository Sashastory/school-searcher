package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import org.sql2o.Sql2o
import sashastory.dev.model.School
import java.time.LocalDate
import java.util.*
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 18.12.2017
 */

object Sql2oSchoolDao : Dao<School> {

    fun getAllSchools(): List<School> = findAll()

    fun getSchoolById(id: Long): School = findById(id) ?: throw NotFoundException("Нет школы с таким $id")

    fun getSchoolsByName(filterText: String): List<School> {
        return Sql2oSchoolDao.dataProvider
                .and { School::schoolName like filterText }
                .getAll()
    }

    fun getSchoolsByPrincipal(principal: String): List<School> {
        return Sql2oSchoolDao.dataProvider
                .and { School::principal like principal }
                .getAll()
    }

    fun getSchoolsByAddress(address: String): List<School> {
        return Sql2oSchoolDao.dataProvider
                .and { School::schoolAddress like address }
                .getAll()
    }

    fun getSchoolsByPhone(phone: String): List<School> {
        return Sql2oSchoolDao.dataProvider
                .and { School::phone like phone }
                .getAll()
    }

    fun getSchoolsByFoundationDate(foundationDate: LocalDate): List<School> {
        return Sql2oSchoolDao.dataProvider
                .and { School::foundationDate eq foundationDate }
                .getAll()
    }

}