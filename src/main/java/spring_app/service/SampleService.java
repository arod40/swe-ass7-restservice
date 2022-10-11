package spring_app.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_app.data.ContestRepository;
import spring_app.data.PersonRepository;
import spring_app.data.TeamRepository;
import spring_app.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SampleService {

    @Autowired
    BusinessRulesValidationService validationService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ContestRepository contestRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


    public void populate() throws ParseException {
        // For dates
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        // Create contest manager
        Person manager = new Person();
        manager.setName("John");
        manager.setBirthdate(simpleDateFormat.parse("01-01-1970"));
        manager.setEmail("john@icpc.com");
        manager.setUniversity("StreetUniversity");
        personRepository.save(manager);

        // Create contestants
        Person contestantAlice = new Person();
        contestantAlice.setName("Alice");
        contestantAlice.setBirthdate(simpleDateFormat.parse("01-01-2001"));
        contestantAlice.setEmail("alice@icpc.com");
        contestantAlice.setUniversity("StreetUniversity");
        personRepository.save(contestantAlice);

        Person contestantBob = new Person();
        contestantBob.setName("Bob");
        contestantBob.setBirthdate(simpleDateFormat.parse("01-01-2003"));
        contestantBob.setEmail("bob@icpc.com");
        contestantBob.setUniversity("StreetUniversity");
        personRepository.save(contestantBob);

        Person contestantCharlie = new Person();
        contestantCharlie.setName("Charlie");
        contestantCharlie.setBirthdate(simpleDateFormat.parse("01-01-2005"));
        contestantCharlie.setEmail("charlie@icpc.com");
        contestantCharlie.setUniversity("StreetUniversity");
        personRepository.save(contestantCharlie);

        Person contestantDaniel = new Person();
        contestantDaniel.setName("Daniel");
        contestantDaniel.setBirthdate(simpleDateFormat.parse("01-01-2004"));
        contestantDaniel.setEmail("daniel@icpc.com");
        contestantDaniel.setUniversity("StreetUniversity");
        personRepository.save(contestantDaniel);

        Person contestantEleanor = new Person();
        contestantEleanor.setName("Eleanor");
        contestantEleanor.setBirthdate(simpleDateFormat.parse("01-01-2002"));
        contestantEleanor.setEmail("eleanor@icpc.com");
        contestantEleanor.setUniversity("StreetUniversity");
        personRepository.save(contestantEleanor);

        Person contestantFrank = new Person();
        contestantFrank.setName("Frank");
        contestantFrank.setBirthdate(simpleDateFormat.parse("01-01-2002"));
        contestantFrank.setEmail("frank@icpc.com");
        contestantFrank.setUniversity("StreetUniversity");
        personRepository.save(contestantFrank);

        // Create coaches
        Person coachJack = new Person();
        coachJack.setName("Jack");
        coachJack.setBirthdate(simpleDateFormat.parse("01-01-1993"));
        coachJack.setEmail("jack@icpc.com");
        coachJack.setUniversity("StreetUniversity");
        personRepository.save(coachJack);

        Person coachKarla = new Person();
        coachKarla.setName("Karla");
        coachKarla.setBirthdate(simpleDateFormat.parse("01-01-1993"));
        coachKarla.setEmail("karla@icpc.com");
        coachKarla.setUniversity("StreetUniversity");
        personRepository.save(coachKarla);

        // Create contests
        Contest contest = new Contest();
        contest.setName("Caribbean Finals");
        contest.setCapacity(15);
        contest.setDate(new Date());
        contest.getManagers().add(manager);
        contestRepository.save(contest);

        Contest subcontest = new Contest();
        subcontest.setName("World Finals");
        subcontest.setCapacity(15);
        subcontest.setDate(new Date());
        subcontest.getManagers().add(manager);
        subcontest.setPreliminaryRound(contest);
        contestRepository.save(subcontest);

        Contest dummyContest = new Contest();
        dummyContest.setName("Dummy");
        dummyContest.setCapacity(1);
        dummyContest.setDate(new Date());
        dummyContest.getManagers().add(manager);
        contestRepository.save(dummyContest);

        // Create teams
        Team team1 = new Team();
        team1.setName("UH++");
        team1.setCoach(coachJack);
        team1.setContestants(new HashSet<>(
                Arrays.asList(contestantAlice, contestantBob,
                        contestantCharlie)));
        team1.setState(State.Accepted);
        team1.setAttendedContest(contest);
        team1.setRank(1);
        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setName("BlackPearl");
        team2.setCoach(coachKarla);
        team2.setContestants(new HashSet<>(
                Arrays.asList(contestantDaniel, contestantEleanor,
                        contestantFrank)));
        team2.setAttendedContest(dummyContest);
        teamRepository.save(team2);

        Person contestantGreg = new Person();
        contestantGreg.setName("Greg");
        contestantGreg.setBirthdate(simpleDateFormat.parse("01-01-2005"));
        contestantGreg.setEmail("greg@icpc.com");
        contestantGreg.setUniversity("StreetUniversity");
        personRepository.save(contestantGreg);

        Person contestantHilary = new Person();
        contestantHilary.setName("Hilary");
        contestantHilary.setBirthdate(simpleDateFormat.parse("01-01-2005"));
        contestantHilary.setEmail("hilary@icpc.com");
        contestantHilary.setUniversity("StreetUniversity");
        personRepository.save(contestantHilary);

        Person contestantIsabella = new Person();
        contestantIsabella.setName("Isabella");
        contestantIsabella.setBirthdate(simpleDateFormat.parse("01-01-2005"));
        contestantIsabella.setEmail("isabella@icpc.com");
        contestantIsabella.setUniversity("StreetUniversity");
        personRepository.save(contestantIsabella);

        Person coachLewis = new Person();
        coachLewis.setName("Lewis");
        coachLewis.setBirthdate(simpleDateFormat.parse("01-01-1993"));
        coachLewis.setEmail("lewis@icpc.com");
        coachLewis.setUniversity("StreetUniversity");
        personRepository.save(coachLewis);

        Team team3 = new Team();
        team3.setName("UH-ClassZero");
        team3.setCoach(coachLewis);
        team3.setContestants(new HashSet<>(
                Arrays.asList(contestantGreg, contestantHilary,
                        contestantIsabella)));
        team3.setRank(6);
        team3.setAttendedContest(contest);
        teamRepository.save(team3);
    }


    public List<Team> findAllTeams() {
        List<Team> result = new ArrayList<>();
        teamRepository.findAll().forEach(x -> result.add(x));
        return result;
    }

    public List<Contest> findAllContests() {
        List<Contest> result = new ArrayList<>();
        contestRepository.findAll().forEach(x -> result.add(x));
        return result;
    }

    public List<Person> findAllPeople() {
        List<Person> result = new ArrayList<>();
        personRepository.findAll().forEach(x -> result.add(x));
        return result;
    }

    public List<Team> getTeamsInContest(Long contestId)
            throws NotFoundException {
        Optional<Contest> contest = contestRepository.findById(contestId);

        if (contest.isPresent()) {
            return contest.get().getTeams().stream().toList();
        } else {
            throw new NotFoundException(
                    "Contest with id " + contestId + " not found");
        }
    }

    public List<PersonCountByAge> getTeamMembersAgeReport() {
        return personRepository.getContestantsGroupedByAge();
    }

    public String getContestOccupancyReport(Long contestId)
            throws NotFoundException {

        Optional<Contest> contest = contestRepository.findById(contestId);
        if (contest.isPresent()) {
            Integer capacity = contest.get().getCapacity();
            Integer occupancy = contest.get().getTeams().size();

            return contest.get().getName() + " is at " + occupancy + "/" +
                    capacity +
                    " occupancy.";
        } else {
            throw new NotFoundException(
                    "Contest with id " + contestId + " not found");
        }


    }

    public Team registerTeamInContest(Team team, Long contestId)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Contest> contestOpt = contestRepository.findById(contestId);
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + contestId + " not found");

        validationService.validateContestIsEditable(contestOpt.get());

        ArrayList missing = new ArrayList<Long>();
        team.setContestants(team.getContestants().stream().map(x -> {
            if (x.getId() != null) {
                Optional<Person> personOpt =
                        personRepository.findById(x.getId());
                if (!personOpt.isPresent()) {
                    missing.add(x.getId());
                    return null;
                } else return personOpt.get();
            } else return x;
        }).collect(Collectors.toSet()));

        if (missing.size() != 0) throw new BusinessConstraintViolationException(
                "Person(s) with ids " + missing + " not found");

        Person coach = team.getCoach();
        if (coach != null && coach.getId() != null) {
            Optional<Person> coachOpt =
                    personRepository.findById(coach.getId());
            if (!coachOpt.isPresent())
                throw new BusinessConstraintViolationException(
                        "Person (coach) with id " + coach.getId() +
                                " not found");
            team.setCoach(coachOpt.get());
        }

        Contest contest = contestOpt.get();
        validationService.validateNonFullContest(contest);
        validationService.validateTeamRegistrationInContest(team,
                contest);

        for (Person person : team.getContestants())
            if (person.getId() == null) personRepository.save(person);
        if (team.getCoach().getId() == null)
            personRepository.save(team.getCoach());
        team.setState(State.Pending);
        team.setRank(null);
        team.setAttendedContest(contest);
        teamRepository.save(team);

        return team;
    }

    public Team editTeam(Team teamPatch)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Team> teamOpt = teamRepository.findById(teamPatch.getId());
        if (!teamOpt.isPresent()) throw new NotFoundException(
                "Team with id " + teamPatch.getId() + " not found");
        Team team = teamOpt.get();
        mapPatchToTeam(teamPatch, team);
        validationService.validateTeamEdit(team);
        teamRepository.save(team);
        return team;
    }

    public Contest editContest(Contest contestPatch)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Contest> contestOpt =
                contestRepository.findById(contestPatch.getId());
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + contestPatch.getId() + " not found");
        Contest contest = contestOpt.get();
        mapPatchToContest(contestPatch, contest);
        validationService.validateContestEdit(contest);
        contestRepository.save(contest);
        return contest;
    }

    public Contest setContestEditable(Long contestId, boolean editable)
            throws NotFoundException {
        Optional<Contest> contestOpt = contestRepository.findById(contestId);
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + contestId + " not found");
        Contest contest = contestOpt.get();
        contest.setEditable(editable);
        contestRepository.save(contest);

        return contest;
    }

    public Team promoteTeam(Long teamId, Long superContestId)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Contest> contestOpt =
                contestRepository.findById(superContestId);
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + superContestId + " not found");
        if (!teamOpt.isPresent()) throw new NotFoundException(
                "Team with id " + teamId + " not found");

        Contest contest = contestOpt.get();
        Team team = teamOpt.get();

        validationService.validateTeamPromotionToSuperContest(team, contest);

        Team promotedTeam = team.clone();
        promotedTeam.setClonedFrom(team);
        promotedTeam.setAttendedContest(contest);
        teamRepository.save(promotedTeam);

        return promotedTeam;
    }

    private void mapPatchToTeam(Team teamPatch, Team team) {
        if (teamPatch.getName() != null) team.setName(teamPatch.getName());
        if (teamPatch.getRank() != null) team.setRank(teamPatch.getRank());
        if (teamPatch.getState() != null) team.setState(teamPatch.getState());
    }

    private void mapPatchToContest(Contest contestPatch, Contest contest) {
        if (contestPatch.getCapacity() != null) contest.setCapacity(
                contestPatch.getCapacity());
        if (contestPatch.getDate() != null)
            contest.setDate(contestPatch.getDate());
        if (contestPatch.getName() != null)
            contest.setName(contestPatch.getName());
        if (contestPatch.getRegistrationAllowed() != null)
            contest.setRegistrationAllowed(
                    contestPatch.getRegistrationAllowed());
        if (contestPatch.getRegistrationFrom() != null)
            contest.setRegistrationFrom(contestPatch.getRegistrationFrom());
        if (contestPatch.getRegistrationTo() != null)
            contest.setRegistrationTo(contestPatch.getRegistrationTo());
    }
}
