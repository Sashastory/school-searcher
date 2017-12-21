package sashastory.dev.rest

import sashastory.dev.model.Application
import sashastory.dev.service.DataService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 21.12.2017
 */
@Path("/applications")
class ApplicationRestController {

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: Long) : Application = DataService.applicationDao.getApplicationById(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() : List<Application> = DataService.applicationDao.getAllApplications()

}