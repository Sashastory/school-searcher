package sashastory.dev.rest

import sashastory.dev.model.Form
import sashastory.dev.service.FormSearchService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author Александр
 * @date 19.12.2017
 */
@Path("/forms")
class FormRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(): List<Form> = FormSearchService.formProvider.getAllForms()

}