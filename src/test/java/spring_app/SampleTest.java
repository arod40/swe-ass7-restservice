package spring_app;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring_app.data.ContestRepository;
import spring_app.data.PersonRepository;
import spring_app.data.TeamRepository;
import spring_app.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SampleTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Before
    public void populate() throws ParseException {
        // For dates
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        // Create contest manager
        Person manager = new Person();
        manager.setName("John");
        manager.setBirthdate(formatter.parse("01-01-1970"));
        manager.setEmail("john@icpc.com");
        manager.setUniversity("StreetUniversity");
        entityManager.persist(manager);

        // Create contestants
        Person contestantAlice = new Person();
        contestantAlice.setName("Alice");
        contestantAlice.setBirthdate(formatter.parse("01-01-1991"));
        contestantAlice.setEmail("alice@icpc.com");
        contestantAlice.setUniversity("StreetUniversity");
        entityManager.persist(contestantAlice);

        Person contestantBob = new Person();
        contestantBob.setName("Bob");
        contestantBob.setBirthdate(formatter.parse("01-01-1993"));
        contestantBob.setEmail("bob@icpc.com");
        contestantBob.setUniversity("StreetUniversity");
        entityManager.persist(contestantBob);

        Person contestantCharlie = new Person();
        contestantCharlie.setName("Charlie");
        contestantCharlie.setBirthdate(formatter.parse("01-01-1995"));
        contestantCharlie.setEmail("charlie@icpc.com");
        contestantCharlie.setUniversity("StreetUniversity");
        entityManager.persist(contestantCharlie);

        Person contestantDaniel = new Person();
        contestantDaniel.setName("Daniel");
        contestantDaniel.setBirthdate(formatter.parse("01-01-1994"));
        contestantDaniel.setEmail("daniel@icpc.com");
        contestantDaniel.setUniversity("StreetUniversity");
        entityManager.persist(contestantDaniel);

        Person contestantEleanor = new Person();
        contestantEleanor.setName("Eleanor");
        contestantEleanor.setBirthdate(formatter.parse("01-01-1992"));
        contestantEleanor.setEmail("eleanor@icpc.com");
        contestantEleanor.setUniversity("StreetUniversity");
        entityManager.persist(contestantEleanor);

        Person contestantFrank = new Person();
        contestantFrank.setName("Frank");
        contestantFrank.setBirthdate(formatter.parse("01-01-1992"));
        contestantFrank.setEmail("frank@icpc.com");
        contestantFrank.setUniversity("StreetUniversity");
        entityManager.persist(contestantFrank);

        Person contestantGreg = new Person();
        contestantGreg.setName("Greg");
        contestantGreg.setBirthdate(formatter.parse("01-01-1995"));
        contestantGreg.setEmail("greg@icpc.com");
        contestantGreg.setUniversity("StreetUniversity");
        entityManager.persist(contestantGreg);

        Person contestantHilary = new Person();
        contestantHilary.setName("Hilary");
        contestantHilary.setBirthdate(formatter.parse("01-01-1995"));
        contestantHilary.setEmail("hilary@icpc.com");
        contestantHilary.setUniversity("StreetUniversity");
        entityManager.persist(contestantHilary);

        Person contestantIsabella = new Person();
        contestantIsabella.setName("Isabella");
        contestantIsabella.setBirthdate(formatter.parse("01-01-1995"));
        contestantIsabella.setEmail("isabella@icpc.com");
        contestantIsabella.setUniversity("StreetUniversity");
        entityManager.persist(contestantIsabella);

        // Create coaches
        Person coachJack = new Person();
        coachJack.setName("Jack");
        coachJack.setBirthdate(formatter.parse("01-01-1993"));
        coachJack.setEmail("jack@icpc.com");
        coachJack.setUniversity("StreetUniversity");
        entityManager.persist(coachJack);

        Person coachKarla = new Person();
        coachKarla.setName("Karla");
        coachKarla.setBirthdate(formatter.parse("01-01-1993"));
        coachKarla.setEmail("karla@icpc.com");
        coachKarla.setUniversity("StreetUniversity");
        entityManager.persist(coachKarla);

        Person coachLewis = new Person();
        coachLewis.setName("Lewis");
        coachLewis.setBirthdate(formatter.parse("01-01-1993"));
        coachLewis.setEmail("lewis@icpc.com");
        coachLewis.setUniversity("StreetUniversity");
        entityManager.persist(coachLewis);

        // Create teams
        Team team1 = new Team();
        team1.setName("UH++");
        team1.setCoach(coachJack);
        team1.setContestants(new HashSet<>(
                Arrays.asList(contestantAlice, contestantBob,
                        contestantCharlie)));
        team1.setState(State.Accepted);
        entityManager.persist(team1);

        Team team2 = new Team();
        team2.setName("BlackPearl");
        team2.setCoach(coachKarla);
        team2.setContestants(new HashSet<>(
                Arrays.asList(contestantDaniel, contestantEleanor,
                        contestantFrank)));
        team2.setState(State.Accepted);
        entityManager.persist(team2);

        Team team3 = new Team();
        team3.setName("UH-ClassZero");
        team3.setCoach(coachLewis);
        team3.setContestants(new HashSet<>(
                Arrays.asList(contestantGreg, contestantHilary,
                        contestantIsabella)));
        team3.setState(State.Accepted);
        entityManager.persist(team3);

        // Create contests
        Contest contest = new Contest();
        contest.setName("Caribbean Finals");
        contest.setCapacity(15);
        contest.setDate(new Date());
        contest.getManagers().add(manager);
        entityManager.persist(contest);

        Contest subcontest = new Contest();
        subcontest.setName("World Finals");
        subcontest.setCapacity(15);
        subcontest.setDate(new Date());
        subcontest.getManagers().add(manager);
        subcontest.setPreliminaryRound(contest);
        subcontest.setTeams(new HashSet<>(Arrays.asList(team1, team2, team3)));
        entityManager.persist(subcontest);
    }

    @Test
    public void teamsInContest() {
        Contest contest = contestRepository.findByName("World Finals");

        contest.getTeams().stream().map(team -> team.getName())
                .forEach(name -> System.out.println(name));
    }

    @Test
    public void studentsByAge() {
        personRepository.getContestantsGroupedByAge()
                .forEach(p -> System.out.println(p));
    }

    @Test
    public void occupancyVsCapacity() {
        Contest contest = contestRepository.findByName("World Finals");

        Integer capacity = contest.getCapacity();
        Integer occupancy = contest.getTeams().size();

        System.out.println(
                contest.getName() + " is at " + occupancy + "/" + capacity +
                        " occupancy.");
    }
}
