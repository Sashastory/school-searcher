package sashastory.dev.dao

import com.github.vok.framework.sql2o.*
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.sun.xml.bind.v2.model.core.ID
import com.vaadin.data.provider.DataProvider
import sashastory.dev.model.AppUser
import sashastory.dev.model.Application
import java.math.BigDecimal
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 17.12.2017
 */
object AppUserDao : Dao<AppUser> {

    private val data: DataProvider<AppUser, Filter<AppUser>?> = dataProvider

    fun getAllUsers(): List<AppUser> = findAll()

    fun getUserById(id: BigDecimal): AppUser = findById(id) ?: throw NotFoundException("Нет пользователя с таким $id")

    fun getUserByUserNameAndPassword(userName: String, password: String): List<AppUser> =
            data.and { AppUser::userName eq userName }.and { AppUser::password eq password }.getAll()

}
