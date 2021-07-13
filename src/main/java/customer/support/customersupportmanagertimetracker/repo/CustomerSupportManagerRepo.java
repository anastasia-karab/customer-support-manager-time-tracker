package customer.support.customersupportmanagertimetracker.repo;

import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSupportManagerRepo extends JpaRepository<CustomerSupportManager, Long> {
}
