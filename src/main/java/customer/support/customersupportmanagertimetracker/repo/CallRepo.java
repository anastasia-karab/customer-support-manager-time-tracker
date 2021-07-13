package customer.support.customersupportmanagertimetracker.repo;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepo extends JpaRepository<Call, Long> {

    @Query("SELECT c FROM Call c WHERE c.activity = :activity")
    List<Call> getCallsByActivity(@Param("activity") Activity activity);
}
