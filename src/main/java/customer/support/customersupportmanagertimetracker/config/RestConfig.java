package customer.support.customersupportmanagertimetracker.config;

import customer.support.customersupportmanagertimetracker.resource.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends ResourceConfig {
    public RestConfig(Class<?>... classes) {
        register(CustomerSupportManagerResource.class);
        register(ActivityResource.class);
        register(CallResource.class);
        register(EmailResource.class);
        register(ChatResource.class);
    }
}
