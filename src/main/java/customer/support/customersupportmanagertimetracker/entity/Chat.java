package customer.support.customersupportmanagertimetracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("startOfTheChat")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "chat_start")
    private Date startOfTheChat;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("endOfTheChat")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "chat_end")
    private Date endOfTheChat;

    @Formula(value = "TIME_TO_SEC(timediff(chat_end, chat_start)) / 60")
    private float chatDuration;

    public Chat(Date startOfTheChat, Date endOfTheChat) {
        this.startOfTheChat = startOfTheChat;
        this.endOfTheChat = endOfTheChat;
    }

    @JsonIgnore
    public Activity getActivity() {
        return activity;
    }
}
