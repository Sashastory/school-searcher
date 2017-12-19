package sashastory.dev.rest

import org.atmosphere.config.service.Get
import sashastory.dev.dao.Sql2oSchoolDao
import sashastory.dev.model.School
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
    fun getAll(): List<School> = Sql2oSchoolDao.getAllSchools()

}