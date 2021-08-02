package customer.support.customersupportmanagertimetracker.service;

import customer.support.customersupportmanagertimetracker.entity.Activity;
import customer.support.customersupportmanagertimetracker.entity.Call;
import customer.support.customersupportmanagertimetracker.entity.Chat;
import customer.support.customersupportmanagertimetracker.entity.Email;
import customer.support.customersupportmanagertimetracker.repo.ActivityRepo;
import customer.support.customersupportmanagertimetracker.repo.CallRepo;
import customer.support.customersupportmanagertimetracker.repo.ChatRepo;
import customer.support.customersupportmanagertimetracker.repo.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static customer.support.customersupportmanagertimetracker.entity.Call.CALL_MAX_KPI;

@Service
public class ActivityService {
    private final ActivityRepo activityRepo;
    private final CallRepo callRepo;
    private final EmailRepo emailRepo;
    private final ChatRepo chatRepo;

    @Autowired
    public ActivityService(ActivityRepo activityRepo,
                           CallRepo callRepo,
                           EmailRepo emailRepo,
                           ChatRepo chatRepo) {
        this.activityRepo = activityRepo;
        this.callRepo = callRepo;
        this.emailRepo = emailRepo;
        this.chatRepo = chatRepo;
    }

    public Activity addActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    public Activity findActivityById(Long id) {
        return activityRepo.getById(id);
    }

    public List<Activity> findAllActivities() {
        return activityRepo.findAll();
    }

    public Activity updateActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    public void deleteActivityById(Long id) {
        activityRepo.deleteById(id);
    }

    public List<Call> findAllCallsByActivityId(Long id) {
        Activity activityById = activityRepo.getById(id);
        List<Call> callsByActivity = callRepo.getCallsByActivity(activityById);

        return callsByActivity;
    }

    public List<Email> findAllEmailsByActivityId(Long id) {
        Activity activityById = activityRepo.getById(id);
        List<Email> emailsByActivity = emailRepo.getEmailsByActivity(activityById);

        return emailsByActivity;
    }

    public List<Chat> findAllChatsByActivityId(Long id) {
        Activity activityById = activityRepo.getById(id);
        List<Chat> chatsByActivity = chatRepo.getChatsByActivity(activityById);

        return chatsByActivity;
    }

    public void addCallToActivity(Long activityId, Call call) {
        Activity activity = activityRepo.getById(activityId);
        call.setActivity(activity);

        List<Call> activityCalls = activity.getCalls();
        activityCalls.add(call);

        callRepo.save(call);
    }

    public void addEmailToActivity(Long activityId, Email email) {
        Activity activity = activityRepo.getById(activityId);
        email.setActivity(activity);

        List<Email> activityEmails = activity.getEmails();
        activityEmails.add(email);

        emailRepo.save(email);
    }

    public void addChatToActivity(Long activityId, Chat chat) {
        Activity activity = activityRepo.getById(activityId);
        chat.setActivity(activity);

        List<Chat> activityChats = activity.getChats();
        activityChats.add(chat);

        chatRepo.save(chat);
    }
}
