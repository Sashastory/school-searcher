package sashastory.dev.dao

import com.github.vok.framework.sql2o.*
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.framework.sql2o.vaadin.getAll
import com.sun.xml.bind.v2.model.core.ID
import sashastory.dev.model.AppUser
import sashastory.dev.model.Application
import javax.ws.rs.NotFoundException

/**
 * @author Александр
 * @date 17.12.2017
 */
object Sql2oAppUserDao : Dao<AppUser> {

    fun getAllUsers(): List<AppUser> = findAll()

    fun getUserById(id: Long): AppUser = findById(id) ?: throw NotFoundException("Нет пользователя с таким $id")

    fun getUserByUserNameAndPassword(userName: String, password: String): List<AppUser> =
            Sql2oAppUserDao.dataProvider
                    .and { AppUser::userName eq userName }
                    .and { AppUser::password eq password }
                    .getAll()


    fun getUserApplications(id: Long): List<Application> =
            Sql2oApplicationDao.dataProvider.and { Application::appUserId eq id }.getAll()

}
