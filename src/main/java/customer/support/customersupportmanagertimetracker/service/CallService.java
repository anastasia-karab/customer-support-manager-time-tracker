package customer.support.customersupportmanagertimetracker.service;

import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.repo.CallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static customer.support.customersupportmanagertimetracker.entity.Call.CALL_MAX_KPI;

@Service
public class CallService {
    private final CallRepo callRepo;
    private final ActivityService activityService;

    @Autowired
    public CallService(CallRepo callRepo, ActivityService activityService) {
        this.callRepo = callRepo;
        this.activityService = activityService;
    }

    public Call findCallById(Long id) {
        return callRepo.getById(id);
    }

    public float getAverageCallDurationForMonthByActivity(Long activityId, int month, int year) {
        List<Call> allCallsByActivityId = activityService.findAllCallsByActivityId(activityId);

        List<Call> monthCalls = new ArrayList<>();
        float sum = 0;
        for (Call c : allCallsByActivityId) {
            Date callDate = c.getStartOfTheCall();
            LocalDate localCallDate = callDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (month == localCallDate.getMonthValue() && year == localCallDate.getYear()) {
                sum += c.getCallDuration();
                monthCalls.add(c);
            }
        }

        return sum / monthCalls.size();
    }

    public boolean callKPIMet(float avgCallDuration) {
        if (avgCallDuration < CALL_MAX_KPI) {
            return true;
        }
        return false;
    }
}
