package sashastory.dev.model

import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * @author Александр
 * @date 18.12.2017
 */
@Table("APPLICATION")
data class Application(

        override var id: BigDecimal? = null,

        var appUserId: BigDecimal? = null,

        var schoolId: BigDecimal? = null,

        var formId: BigDecimal? = null,

        var applicationDate: Date? = null

) : Entity<BigDecimal>, Serializable