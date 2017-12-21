package sashastory.dev.rest

import sashastory.dev.model.School
import sashastory.dev.service.DataService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 19.12.2017
 */
@Path("/schools")
class SchoolRestController {

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: Long) : School = DataService.schoolDao.getSchoolById(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<School> = DataService.schoolDao.getAllSchools()

}