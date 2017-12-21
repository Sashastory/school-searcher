package sashastory.dev.service

import sashastory.dev.dao.AppUserDao
import sashastory.dev.dao.ApplicationDao
import sashastory.dev.dao.FormDao
import sashastory.dev.dao.SchoolDao

/**
 * @author Александр
 * @date 21.12.2017
 */
object DataService {

    val applicationDao: ApplicationDao = ApplicationDao()
    val appUserDao: AppUserDao = AppUserDao()
    val formDao: FormDao = FormDao()
    val schoolDao: SchoolDao = SchoolDao()

}