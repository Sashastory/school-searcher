package sashastory.dev.rest

import sashastory.dev.model.AppUser
import sashastory.dev.service.SecurityService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 17.12.2017
 */
@Path("/users")
class AppUserRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<AppUser> = SecurityService.appUserProvider.getAllUsers()

}