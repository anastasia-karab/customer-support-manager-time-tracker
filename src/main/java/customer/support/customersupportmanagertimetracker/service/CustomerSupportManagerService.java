package customer.support.customersupportmanagertimetracker.service;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import customer.support.customersupportmanagertimetracker.repo.ActivityRepo;
import customer.support.customersupportmanagertimetracker.repo.CustomerSupportManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSupportManagerService {
    private final CustomerSupportManagerRepo customerSupportManagerRepo;
    private final ActivityRepo activityRepo;

    @Autowired
    public CustomerSupportManagerService(CustomerSupportManagerRepo customerSupportManagerRepo,
                                         ActivityRepo activityRepo) {
        this.customerSupportManagerRepo = customerSupportManagerRepo;
        this.activityRepo = activityRepo;
    }

    public CustomerSupportManager addManager(CustomerSupportManager manager) {
        return customerSupportManagerRepo.save(manager);
    }

    public CustomerSupportManager findManagerById(Long id) {
        return customerSupportManagerRepo.getById(id);
    }

    public List<CustomerSupportManager> findAllManagers() {
        return customerSupportManagerRepo.findAll();
    }

    public Activity getManagerActivityById(Long id) {
        CustomerSupportManager managerById = customerSupportManagerRepo.getById(id);
        Activity activityByManager = activityRepo.getActivityByManager(managerById);

        return activityByManager;
    }

    public CustomerSupportManager updateManager(CustomerSupportManager manager) {
        return customerSupportManagerRepo.save(manager);
    }

    public void deleteManagerById(Long id) {
        customerSupportManagerRepo.deleteById(id);
    }
}
