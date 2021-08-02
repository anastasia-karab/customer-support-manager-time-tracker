package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Chat;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import customer.support.customersupportmanagertimetracker.service.ChatService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Month;
import java.util.List;

@Path("/chat")
public class ChatResource {
    ActivityService activityService;
    ChatService chatService;

    @Autowired
    public ChatResource(ActivityService activityService, ChatService chatService) {
        this.activityService = activityService;
        this.chatService = chatService;
    }

    @GET
    @Produces("application/json")
    @Path("/all/{id}")
    public List<Chat> getAllChatsByActivityId(@PathParam("id") Long id) {
        return activityService.findAllChatsByActivityId(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new/{id}")
    public Response addNewChatToActivity(@PathParam("id") Long id, Chat chat) throws JSONException {
        activityService.addChatToActivity(id, chat);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Chat getChatById(@PathParam("id") Long id) {
        return chatService.findChatById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/average/duration/{activityId}/{month}/{year}")
    public Response calculateAverageChatDuration(@PathParam("activityId") Long activityId,
                                                 @PathParam("month") int month,
                                                 @PathParam("year") int year) throws JSONException {
        float avgMonthChatDuration = chatService
                .getAverageChatDurationForMonthByActivity(activityId, month, year);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("period", Month.of(month) + ", " + year);
        jsonObject.put("average_duration", String.format("%.2f", avgMonthChatDuration));
        jsonObject.put("kpi_met", chatService.chatKPIMet(avgMonthChatDuration));

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
