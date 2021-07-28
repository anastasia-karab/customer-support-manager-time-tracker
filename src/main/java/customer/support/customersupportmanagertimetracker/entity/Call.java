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
@Table(name = "calls")
public class Call {
    public static final float CALL_MAX_KPI = 10f;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("startOfTheCall")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "call_start")
    private Date startOfTheCall;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("endOfTheCall")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "call_end")
    private Date endOfTheCall;

    @Formula(value = "TIME_TO_SEC(timediff(call_end, call_start)) / 60")
    private float callDuration;

    public Call(Date startOfTheCall, Date endOfTheCall) {
        this.startOfTheCall = startOfTheCall;
        this.endOfTheCall = endOfTheCall;
    }

    @JsonIgnore
    public Activity getActivity() {
        return activity;
    }
}
