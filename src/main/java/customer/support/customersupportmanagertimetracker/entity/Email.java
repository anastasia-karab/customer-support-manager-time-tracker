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
@Table(name = "emails")
public class Email {
    public static final float EMAIL_MAX_KPI = 15f;

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

    @Formula(value = "TIME_TO_SEC(timediff(email_end, email_start)) / 60")
    private float emailDuration;

    public Email(Date startOfTheEmail, Date endOfTheEmail) {
        this.startOfTheEmail = startOfTheEmail;
        this.endOfTheEmail = endOfTheEmail;
    }

    @JsonIgnore
    public Activity getActivity() {
        return activity;
    }
}
