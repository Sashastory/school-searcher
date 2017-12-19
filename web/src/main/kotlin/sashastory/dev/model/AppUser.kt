package sashastory.dev.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Filter
import com.github.vok.framework.sql2o.Table
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.vaadin.data.provider.DataProvider
import sashastory.dev.dao.Sql2oApplicationDao
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.Size

/**
 * @author Александр
 * @date 17.12.2017
 */
@Table("APP_USER")
data class AppUser(

        override var id: Long? = null,

        @field:NotNull
        @field:Size(min = 5, max = 20)
        var userName: String? = null,

        @field:NotNull
        @field:Size(min = 4, max = 20)
        var password: String? = null,

        @field:NotNull
        @field:Size(min = 10, max = 50)
        var fullName: String? = null,

        @field:NotNull
        @field:PastOrPresent
        var dateOfBirth: LocalDate? = null,

        @field:NotNull
        var sex: Sex? = null,

        @field:NotNull
        @field:Email
        var email: String? = null,

        var location: String? = null,

        @field:Size(min = 11, max = 11)
        var phone: String? = null

) : Entity<Long>, Serializable {

        @get:JsonIgnore
        val applications: DataProvider<Application, Filter<Application>?>

                get() = Sql2oApplicationDao.dataProvider.and { Application::appUserId eq id}
}

enum class Sex {
    Мужской,
    Женский
}