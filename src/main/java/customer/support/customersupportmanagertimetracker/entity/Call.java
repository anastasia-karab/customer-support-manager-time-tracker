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
@Table(name = "calls")
public class Call {
//    private static final Duration KPI = Duration.ofMinutes(15);

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

/*    @Formula(value = "timediff(call_end, call_start)")
    private Date callDuration;*/

    private long callDuration;

    public Call(Date startOfTheCall, Date endOfTheCall) {
        this.startOfTheCall = startOfTheCall;
        this.endOfTheCall = endOfTheCall;
    }

    @JsonIgnore
    public Activity getActivity() {
        return activity;
    }

    @Temporal(TemporalType.TIME)
    @JsonProperty("callDuration")
    public long getCallDuration() {
        long diffInMillis = endOfTheCall.getTime() - startOfTheCall.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);

        return diffInMinutes;
    }
}
