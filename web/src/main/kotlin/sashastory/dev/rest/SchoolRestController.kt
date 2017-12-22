package sashastory.dev.rest

import sashastory.dev.model.School
import sashastory.dev.service.SchoolSearchService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 19.12.2017
 */
@Path("/schools")
class SchoolRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<School> = SchoolSearchService.schoolProvider.getAllSchools()

}