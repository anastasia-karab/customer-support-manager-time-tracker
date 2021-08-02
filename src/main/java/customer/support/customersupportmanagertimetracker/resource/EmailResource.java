package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Email;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import customer.support.customersupportmanagertimetracker.service.EmailService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Month;
import java.util.List;

@Path("/email")
public class EmailResource {
    ActivityService activityService;
    EmailService emailService;

    @Autowired
    public EmailResource(ActivityService activityService, EmailService emailService) {
        this.activityService = activityService;
        this.emailService = emailService;
    }

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
        return emailService.findEmailById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/average/duration/{activityId}/{month}/{year}")
    public Response calculateAverageEmailDuration(@PathParam("activityId") Long activityId,
                                                 @PathParam("month") int month,
                                                 @PathParam("year") int year) throws JSONException {
        float avgMonthEmailDuration = emailService
                .getAverageEmailDurationForMonthByActivity(activityId, month, year);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("period", Month.of(month) + ", " + year);
        jsonObject.put("average_duration", String.format("%.2f", avgMonthEmailDuration));
        jsonObject.put("kpi_met", emailService.emailKPIMet(avgMonthEmailDuration));

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
