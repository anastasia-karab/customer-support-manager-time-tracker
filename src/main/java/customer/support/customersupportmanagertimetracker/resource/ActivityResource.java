package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activity")
public class ActivityResource {
    @Autowired
    ActivityService activityService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new")
    public Response addNewActivity(Activity activity) throws JSONException {
        activityService.addActivity(activity);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
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
    public Response updateActivity(@PathParam("id") Long id) throws JSONException {
        Activity activityById = activityService.findActivityById(id);
        activityService.updateActivity(activityById);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "updated");

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteActivityById(@PathParam("id") Long id) throws JSONException {
        activityService.deleteActivityById(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "deleted");

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
