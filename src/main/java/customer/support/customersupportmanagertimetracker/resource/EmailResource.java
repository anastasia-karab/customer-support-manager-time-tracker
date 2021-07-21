package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Email;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

@Path("/email")
public class EmailResource {
    @Autowired
    ActivityService activityService;

    @GET
    @Produces("application/json")
    @Path("/all/{id}")
    public List<Email> getAllEmailsByActivityId(@PathParam("id") Long id) {
        return activityService.findAllEmailsByActivityId(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new/{id}")
    public List<Email> addNewEmailToActivity(@PathParam("id") Long id, Email email) {
        activityService.addEmailToActivity(id, email);
        return getAllEmailsByActivityId(id);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Email getEmailById(@PathParam("id") Long id) {
        return activityService.findEmailById(id);
    }
}
