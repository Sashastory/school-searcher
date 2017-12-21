package sashastory.dev.service

import sashastory.dev.model.Application

/**
 * @author Александр
 * @date 21.12.2017
 */
object ApplicationService {

    fun apply(application: Application) {
        application.save()
    }

    fun getCurrentUserApplications(id: Long): List<Application>
            = DataService.applicationDao.getUserApplications(id)
}