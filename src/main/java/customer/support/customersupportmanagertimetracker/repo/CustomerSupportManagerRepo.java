package customer.support.customersupportmanagertimetracker.repo;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.CustomerSupportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerSupportManagerRepo extends JpaRepository<CustomerSupportManager, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update CustomerSupportManager c set c.activity = :activity where c.id = :managerId")
    void attachActivityToManagerById(@Param("activity") Activity activity,
                                     @Param("managerId") Long managerId);
}
