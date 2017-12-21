package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.Filter
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.SqlDataProvider
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.vaadin.data.provider.DataProvider
import org.sql2o.Sql2o
import sashastory.dev.model.Form
import sashastory.dev.model.School
import java.math.BigDecimal
import java.time.LocalDate
import javax.ws.rs.NotFoundException


/**
 * @author Александр
 * @date 18.12.2017
 */

class SchoolDao : Dao<School> {

    private val data : DataProvider<School, Filter<School>?> = dataProvider

    fun getAllSchools(): List<School> = findAll()

    fun getSchoolById(id: Long): School = findById(id) ?: throw NotFoundException("Нет школы с таким $id")

    fun getSchoolsByName(name: String): List<School> =
            data.and { School::schoolName like name }.getAll()

    fun getSchoolsByPrincipal(principal: String): List<School> =
            data.and { School::principal like principal }.getAll()

    fun getSchoolsByAddress(address: String): List<School> =
            data.and { School::schoolAddress like address }.getAll()

    fun getSchoolsByPhone(phone: String): List<School> =
            data.and { School::phone like phone }.getAll()

    fun getSchoolsByFoundationDate(foundationDate: LocalDate): List<School> =
            data.and { School::foundationDate eq foundationDate }.getAll()

    fun getSchoolsWithFreeSpots(): List<School> {
        val dp = SqlDataProvider(School::class.java,
            "select DISTINCT s.id as id, s.schoolName as schoolName, s.principal as principal," +
             " s.schoolAddress as schoolAddress, s.phone as phone, s.foundationDate as foundationDate" +
             " from SCHOOL s join FORM f on s.id = f.schoolId" +
             " and f.studentAmount < :amount {{WHERE}} order by s.schoolName {{ORDER}} {{PAGING}}",
                mapOf("amount" to 30), { it })
        return dp.getAll()
    }
}