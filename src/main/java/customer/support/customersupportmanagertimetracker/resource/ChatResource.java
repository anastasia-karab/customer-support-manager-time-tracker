package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Chat;
import customer.support.customersupportmanagertimetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

@Path("/chat")
public class ChatResource {
    @Autowired
    ActivityService activityService;

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
    public List<Chat> addNewChatToActivity(@PathParam("id") Long id, Chat chat) {
        activityService.addChatToActivity(id, chat);
        return getAllChatsByActivityId(id);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Chat getChatById(@PathParam("id") Long id) {
        return activityService.findChatById(id);
    }
}
