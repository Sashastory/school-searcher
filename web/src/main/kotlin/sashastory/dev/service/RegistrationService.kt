package sashastory.dev.service

import sashastory.dev.model.AppUser

/**
 * @author Александр
 * @date 21.12.2017
 */
object RegistrationService {

    fun register(user: AppUser) {
        user.save()
    }
}