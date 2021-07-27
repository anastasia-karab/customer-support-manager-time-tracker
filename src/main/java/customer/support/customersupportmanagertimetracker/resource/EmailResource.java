package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Email;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    public Response addNewEmailToActivity(@PathParam("id") Long id, Email email) throws JSONException {
        activityService.addEmailToActivity(id, email);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Email getEmailById(@PathParam("id") Long id) {
        return activityService.findEmailById(id);
    }
}
