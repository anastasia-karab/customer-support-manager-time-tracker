package customer.support.customersupportmanagertimetracker.repo;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.manager = :manager")
    Activity getActivityByManager(@Param("manager") CustomerSupportManager manager);
}
