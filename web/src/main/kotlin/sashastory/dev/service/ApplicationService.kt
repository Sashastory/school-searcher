package sashastory.dev.service

import sashastory.dev.model.Application
import sashastory.dev.model.provider.ApplicationProvider

/**
 * @author Александр
 * @date 21.12.2017
 */
object ApplicationService {

    val applicationProvider = ApplicationProvider

    fun apply(application: Application) {
        applicationProvider.saveApplication(application)
    }


}