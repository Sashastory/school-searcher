package sashastory.dev.rest

import org.atmosphere.config.service.Get
import sashastory.dev.dao.AppUserDao
import sashastory.dev.model.AppUser
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 17.12.2017
 */

@Path("/users")
class AppUserRestController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<AppUser> = AppUserDao.getAllUsers()

}