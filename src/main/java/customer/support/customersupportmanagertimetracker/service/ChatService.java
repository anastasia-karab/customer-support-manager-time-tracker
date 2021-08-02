package customer.support.customersupportmanagertimetracker.service;

import customer.support.customersupportmanagertimetracker.entity.Chat;
import customer.support.customersupportmanagertimetracker.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static customer.support.customersupportmanagertimetracker.entity.Chat.CHAT_MAX_KPI;

@Service
public class ChatService {
    private final ChatRepo chatRepo;
    private final ActivityService activityService;

    @Autowired
    public ChatService(ChatRepo chatRepo, ActivityService activityService) {
        this.chatRepo = chatRepo;
        this.activityService = activityService;
    }

    public Chat findChatById(Long id) { return chatRepo.getById(id); }

    public float getAverageChatDurationForMonthByActivity(Long activityId, int month, int year) {
        List<Chat> allChatsByActivityId = activityService.findAllChatsByActivityId(activityId);

        List<Chat> monthChats = new ArrayList<>();
        float sum = 0;
        for (Chat c : allChatsByActivityId) {
            Date chatDate = c.getStartOfTheChat();
            LocalDate localChatDate = chatDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (month == localChatDate.getMonthValue() && year == localChatDate.getYear()) {
                sum += c.getChatDuration();
                monthChats.add(c);
            }
        }

        return sum / monthChats.size();
    }

    public boolean chatKPIMet(float avgChatDuration) {
        if (avgChatDuration < CHAT_MAX_KPI) {
            return true;
        }
        return false;
    }
}
