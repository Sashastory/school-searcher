package sashastory.dev.rest

import sashastory.dev.model.Application
import sashastory.dev.service.ApplicationService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 21.12.2017
 */
@Path("/applications")
class ApplicationRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() : List<Application> = ApplicationService.applicationProvider.getAllApplications()

}