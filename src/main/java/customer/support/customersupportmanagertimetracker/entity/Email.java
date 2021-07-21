package customer.support.customersupportmanagertimetracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "emails")
public class Email {
//    private static final Duration KPI = Duration.ofMinutes(10);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("startOfTheEmail")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "email_start")
    private Date startOfTheEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("endOfTheEmail")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "email_end")
    private Date endOfTheEmail;

    private long emailDuration;

    public Email(Date startOfTheEmail, Date endOfTheEmail) {
        this.startOfTheEmail = startOfTheEmail;
        this.endOfTheEmail = endOfTheEmail;
    }

    @JsonIgnore
    public Activity getActivity() {
        return activity;
    }

    @Temporal(TemporalType.TIME)
    @JsonProperty("emailDuration")
    public long getEmailDuration() {
        long diffInMillis = endOfTheEmail.getTime() - startOfTheEmail.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);

        return diffInMinutes;
    }
}
