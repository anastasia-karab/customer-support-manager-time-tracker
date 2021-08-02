package customer.support.customersupportmanagertimetracker.service;

import customer.support.customersupportmanagertimetracker.entity.Email;
import customer.support.customersupportmanagertimetracker.repo.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static customer.support.customersupportmanagertimetracker.entity.Email.EMAIL_MAX_KPI;

@Service
public class EmailService {
    private final EmailRepo emailRepo;
    private final ActivityService activityService;

    @Autowired
    public EmailService(EmailRepo emailRepo, ActivityService activityService) {
        this.emailRepo = emailRepo;
        this.activityService = activityService;
    }

    public Email findEmailById(Long id) { return emailRepo.getById(id); }

    public float getAverageEmailDurationForMonthByActivity(Long activityId, int month, int year) {
        List<Email> allEmailsByActivityId = activityService.findAllEmailsByActivityId(activityId);

        List<Email> monthEmails = new ArrayList<>();
        float sum = 0;
        for (Email e : allEmailsByActivityId) {
            Date emailDate = e.getStartOfTheEmail();
            LocalDate localEmailDate = emailDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (month == localEmailDate.getMonthValue() && year == localEmailDate.getYear()) {
                sum += e.getEmailDuration();
                monthEmails.add(e);
            }
        }

        return sum / monthEmails.size();
    }

    public boolean emailKPIMet(float avgEmailDuration) {
        if (avgEmailDuration < EMAIL_MAX_KPI) {
            return true;
        }
        return false;
    }
}
