package spring_app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.persistence.*;

import static java.util.Calendar.*;

@Data
@Entity
@EqualsAndHashCode(
        exclude = {"contestsManaged", "teamsAsContestant", "teamsAsCoach"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date birthdate;

    private String email;

    private String name;

    private String university;

    private Integer age;

    @ManyToMany
    @JoinTable(name = "PERSON_CONTEST",
            joinColumns = {@JoinColumn(name = "PERSON_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CONTEST_ID",
                    referencedColumnName = "ID")})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contest> contestsManaged = new HashSet();

    @ManyToMany
    @JoinTable(name = "PERSON_TEAM",
            joinColumns = {@JoinColumn(name = "PERSON_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "TEAM_ID",
                    referencedColumnName = "ID")})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Person> teamsAsContestant = new HashSet();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "coach")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Team> teamsAsCoach = new HashSet();

    public void setBirthdate(Date birthdate) {
        this.age = this.getDiffYears(birthdate, new Date());
        this.birthdate = birthdate;
    }

    private static Integer getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
