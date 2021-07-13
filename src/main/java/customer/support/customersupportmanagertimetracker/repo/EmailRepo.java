package customer.support.customersupportmanagertimetracker.repo;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepo extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE e.activity = :activity")
    List<Email> getEmailsByActivity(@Param("activity") Activity activity);
}
