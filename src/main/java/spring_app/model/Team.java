package spring_app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "clonedFrom")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer rank;

    private State state = State.Pending;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Contest attendedContest;

    @ManyToMany
    @JoinTable(name = "PERSON_TEAM",
            joinColumns = {@JoinColumn(name = "TEAM_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID",
                    referencedColumnName = "ID")})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Person> contestants = new HashSet();

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Person coach;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Team clonedFrom;

    @OneToOne(mappedBy = "clonedFrom")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Team clonedTo;

    public Team clone() {
        Team cloned = new Team();
        cloned.setState(State.Pending);
        cloned.setAttendedContest(attendedContest);
        cloned.setName(name);
        cloned.setCoach(coach);
        contestants.forEach(x -> cloned.getContestants().add(x));

        return cloned;
    }
}
