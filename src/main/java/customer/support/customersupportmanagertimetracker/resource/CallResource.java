package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
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
    public List<Call> addNewCallToActivity(@PathParam("id") Long id, Call call) {
        activityService.addCallToActivity(id, call);
        return getAllCallsByActivityId(id);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Call getCallById(@PathParam("id") Long id) {
        return activityService.findCallById(id);
    }
}
