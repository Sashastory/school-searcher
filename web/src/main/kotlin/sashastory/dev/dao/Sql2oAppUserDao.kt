package sashastory.dev.dao

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.findAll
import com.github.vok.framework.sql2o.findById
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.AppUser
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 17.12.2017
 */
object Sql2oAppUserDao : Dao<AppUser> {

    fun getAllUsers(): List<AppUser> = findAll()

    fun getUserById(id: Long): AppUser = findById(id) ?: throw NotFoundException("Нет пользователя с таким $id")

    fun getUserByUserNameAndPassword(userName: String, password: String): List<AppUser> {
        return Sql2oAppUserDao.dataProvider
                .and { AppUser::userName eq userName }
                .and { AppUser::password eq password }
                .getAll()
    }

}
