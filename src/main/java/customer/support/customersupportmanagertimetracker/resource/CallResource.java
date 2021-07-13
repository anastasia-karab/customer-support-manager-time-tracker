package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
