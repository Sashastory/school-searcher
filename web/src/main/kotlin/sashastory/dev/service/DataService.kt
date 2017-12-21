package sashastory.dev.service

import sashastory.dev.model.dao.AppUserDao
import sashastory.dev.model.dao.ApplicationDao
import sashastory.dev.model.dao.FormDao
import sashastory.dev.model.dao.SchoolDao

/**
 * @author Александр
 * @date 21.12.2017
 */
object DataService {

    val applicationDao: ApplicationDao = ApplicationDao
    val appUserDao: AppUserDao = AppUserDao
    val formDao: FormDao = FormDao
    val schoolDao: SchoolDao = SchoolDao

}