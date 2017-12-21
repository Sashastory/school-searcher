package sashastory.dev.rest

import org.atmosphere.config.service.Get
import sashastory.dev.dao.FormDao
import sashastory.dev.model.Form
import sashastory.dev.service.DataService
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 19.12.2017
 */
@Path("/forms")
class FormRestController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<Form> = DataService.formDao.getAllForms()

}