package sashastory.dev.rest

import org.atmosphere.config.service.Get
import sashastory.dev.model.School
import sashastory.dev.service.DataService
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 19.12.2017
 */
@Path("/schools")
class SchoolRestController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<School> = DataService.schoolDao.getAllSchools()

}