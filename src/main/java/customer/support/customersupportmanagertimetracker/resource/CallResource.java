package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/call")
public class CallResource {
    @Autowired
    ActivityService activityService;

    @GET
    @Produces("application/json")
    @Path("/all/{id}")
    public List<Call> getAllCallsByActivityId(@PathParam("id") Long id) {
        return activityService.findAllCallsByActivityId(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new/{id}")
    public Response addNewCallToActivity(@PathParam("id") Long id, Call call) throws JSONException {
        activityService.addCallToActivity(id, call);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Call getCallById(@PathParam("id") Long id) {
        return activityService.findCallById(id);
    }
}
