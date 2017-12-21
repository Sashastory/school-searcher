package sashastory.dev.model.dao

import com.github.vok.framework.sql2o.*
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.vaadin.data.provider.DataProvider
import sashastory.dev.model.AppUser
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 17.12.2017
 */
object AppUserDao : Dao<AppUser> {

    private val data: DataProvider<AppUser, Filter<AppUser>?> = dataProvider

    fun getAllUsers(): List<AppUser> = findAll()

    fun getUserById(id: Long): AppUser = findById(id) ?: throw NotFoundException("Нет пользователя с таким $id")

    fun getUserByUserNameAndPassword(userName: String, password: String): List<AppUser>
            = data.and { AppUser::userName eq userName }.and { AppUser::password eq password }.getAll()


}
