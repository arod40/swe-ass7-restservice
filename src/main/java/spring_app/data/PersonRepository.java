package spring_app.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring_app.model.Person;
import spring_app.model.PersonCountByAge;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {


    @Query("SELECT new spring_app.model.PersonCountByAge(p.age, COUNT(p.age)) FROM Person p WHERE SIZE(p.teamsAsContestant) > 0 GROUP BY p.age ORDER BY p.age DESC")
    List<PersonCountByAge> getContestantsGroupedByAge();
}