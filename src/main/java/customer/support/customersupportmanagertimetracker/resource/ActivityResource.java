package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

@Path("/activity")
public class ActivityResource {
    @Autowired
    ActivityService activityService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new")
    public Activity addNewActivity(Activity activity) {
        return activityService.addActivity(activity);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Activity getActivityById(@PathParam("id") Long id) {
        return activityService.findActivityById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<Activity> getAllActivities() {
        return activityService.findAllActivities();
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update/{id}")
    public Activity updateActivity(@PathParam("id") Long id) {
        Activity activityById = activityService.findActivityById(id);
        return activityService.updateActivity(activityById);
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteActivityById(@PathParam("id") Long id) {
        activityService.deleteActivityById(id);
    }
}
