package spring_app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"teams", "subsequentRounds"})
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer capacity;

    private Date date;

    private String name;

    private Boolean registrationAllowed;

    private Date registrationFrom;

    private Date registrationTo;

    private boolean editable = true;

    @ManyToMany
    @JoinTable(name = "PERSON_CONTEST",
            joinColumns = {@JoinColumn(name = "CONTEST_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID",
                    referencedColumnName = "ID")})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Person> managers = new HashSet();

    @OneToMany(mappedBy = "preliminaryRound")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contest> subsequentRounds = new HashSet();

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Contest preliminaryRound;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "attendedContest")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Team> teams = new HashSet();

}
