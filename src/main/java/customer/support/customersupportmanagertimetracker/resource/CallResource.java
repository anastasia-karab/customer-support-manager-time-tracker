package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import customer.support.customersupportmanagertimetracker.service.CallService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Month;
import java.util.List;

@Path("/call")
public class CallResource {
    ActivityService activityService;
    CallService callService;

    @Autowired
    public CallResource(ActivityService activityService, CallService callService) {
        this.activityService = activityService;
        this.callService = callService;
    }

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
        return callService.findCallById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/average/duration/{activityId}/{month}/{year}")
    public Response calculateAverageCallDuration(@PathParam("activityId") Long activityId,
                                   @PathParam("month") int month,
                                   @PathParam("year") int year) throws JSONException {
        float avgMonthCallDuration = callService
                .getAverageCallDurationForMonthByActivity(activityId, month, year);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("period", Month.of(month) + ", " + year);
        jsonObject.put("average_duration", String.format("%.2f", avgMonthCallDuration));
        jsonObject.put("kpi_met", callService.callKPIMet(avgMonthCallDuration));

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
