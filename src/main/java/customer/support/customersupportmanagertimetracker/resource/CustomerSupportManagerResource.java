package customer.support.customersupportmanagertimetracker.resource;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import customer.support.customersupportmanagertimetracker.service.CustomerSupportManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

@Path("/manager")
public class CustomerSupportManagerResource {
    @Autowired
    CustomerSupportManagerService customerSupportManagerService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/new")
    public CustomerSupportManager addNewManager(CustomerSupportManager manager) {
        return customerSupportManagerService.addManager(manager);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/activity/{activityId}/{managerId}")
    public CustomerSupportManager attachActivityToManager(@PathParam("activityId") Long activityId,
                                                          @PathParam("managerId") Long managerId) {
        customerSupportManagerService.setActivityToManager(activityId, managerId);
        return customerSupportManagerService.findManagerById(managerId);
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
    public CustomerSupportManager updateManager(@PathParam("id") Long id) {
        CustomerSupportManager managerById = customerSupportManagerService.findManagerById(id);
        return customerSupportManagerService.updateManager(managerById);
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteManagerNyId(@PathParam("id") Long id) {
        customerSupportManagerService.deleteManagerById(id);
    }
}
