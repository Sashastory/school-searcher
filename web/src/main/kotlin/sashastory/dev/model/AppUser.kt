package sashastory.dev.model

import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import java.io.Serializable
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

) : Entity<Long>, Serializable

enum class Sex {
    Мужской,
    Женский
}