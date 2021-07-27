package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import customer.support.customersupportmanagertimetracker.service.CustomerSupportManagerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/manager")
public class CustomerSupportManagerResource {
    @Autowired
    CustomerSupportManagerService customerSupportManagerService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new")
    public Response addNewManager(CustomerSupportManager manager) throws JSONException {
        customerSupportManagerService.addManager(manager);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/activity/{activityId}/{managerId}")
    public Response attachActivityToManager(@PathParam("activityId") Long activityId,
                                            @PathParam("managerId") Long managerId) throws JSONException {
        customerSupportManagerService.setActivityToManager(activityId, managerId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");

        return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CustomerSupportManager findManagerById(@PathParam("id") Long id) {
        return customerSupportManagerService.findManagerById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/activity/{id}")
    public Activity findActivityByManagerId(@PathParam("id") Long id) {
        return customerSupportManagerService.getManagerActivityById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CustomerSupportManager> findAllManagers() {
        return customerSupportManagerService.findAllManagers();
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update/{id}")
    public Response updateManager(@PathParam("id") Long id) throws JSONException {
        CustomerSupportManager managerById = customerSupportManagerService.findManagerById(id);
        customerSupportManagerService.updateManager(managerById);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "updated");

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteManagerNyId(@PathParam("id") Long id) throws JSONException {
        customerSupportManagerService.deleteManagerById(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "deleted");

        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
