package sashastory.dev.model

import com.github.vok.framework.sql2o.Entity
import com.github.vok.framework.sql2o.Table
import java.io.Serializable
import java.math.BigDecimal
import javax.validation.constraints.Max

/**
 * @author Александр
 * @date 18.12.2017
 */

@Table("FORM")
data class Form(
        override var id: BigDecimal? = null,

        var formNumber: String? = null,

        var teacherName: String? = null,

        @Max(30)
        var studentAmount: Int? = null,

        var schoolId: Long? = null

) : Entity<BigDecimal>, Serializable