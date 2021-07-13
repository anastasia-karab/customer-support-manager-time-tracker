package customer.support.customersupportmanagertimetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "activity")
    private CustomerSupportManager manager;

    @OneToMany(mappedBy = "activity")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Call> calls;

    @OneToMany(mappedBy = "activity")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @OneToMany(mappedBy = "activity")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Chat> chats;
}
