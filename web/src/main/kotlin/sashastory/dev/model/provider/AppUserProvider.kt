package sashastory.dev.model.provider

import com.github.vok.framework.sql2o.vaadin.EntityDataProvider
import com.github.vok.framework.sql2o.vaadin.and
import com.github.vok.framework.sql2o.vaadin.getAll
import sashastory.dev.model.AppUser

/**
 * @author Александр
 * @date 17.12.2017
 */
object AppUserProvider {

    private val data = EntityDataProvider(AppUser::class.java)

    fun getAllUsers(): List<AppUser> = data.getAll()

    fun getUserByUserNameAndPassword(userName: String, password: String): List<AppUser>
            = data.and { AppUser::userName eq userName }.and { AppUser::password eq password }.getAll()

    fun saveUser(user: AppUser) {
        user.save()
    }
}
