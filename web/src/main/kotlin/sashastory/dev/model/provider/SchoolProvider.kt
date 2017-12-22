package sashastory.dev.model.provider

import com.github.vok.framework.sql2o.vaadin.EntityDataProvider
import com.github.vok.framework.sql2o.vaadin.SqlDataProvider
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.School
import java.time.LocalDate


/**
 * @author Александр
 * @date 18.12.2017
 */

object SchoolProvider {

    private val data = EntityDataProvider(School::class.java)

    fun getAllSchools(): List<School> = data.getAll()

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