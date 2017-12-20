package sashastory.dev.model

import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

/**
 * @author Александр
 * @date 18.12.2017
 */
@Table("SCHOOL")
data class School(

        override var id: BigDecimal? = null,

        var schoolName: String? = null,

        var principal: String? = null,

        var schoolAddress: String? = null,

        var phone: String? = null,

        var foundationDate: LocalDate? = null

) : Entity<BigDecimal>, Serializable