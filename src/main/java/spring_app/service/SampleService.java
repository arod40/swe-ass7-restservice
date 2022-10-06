package spring_app.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_app.data.ContestRepository;
import spring_app.data.PersonRepository;
import spring_app.data.TeamRepository;
import spring_app.dto.ContestEditDto;
import spring_app.dto.TeamEditDto;
import spring_app.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        contestantIsabella.setBirthdate(simpleDateFormat.parse("01-01-1995"));
        contestantIsabella.setEmail("isabella@icpc.com");
        contestantIsabella.setUniversity("StreetUniversity");
        personRepository.save(contestantIsabella);

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

        Person coachLewis = new Person();
        coachLewis.setName("Lewis");
        coachLewis.setBirthdate(simpleDateFormat.parse("01-01-1993"));
        coachLewis.setEmail("lewis@icpc.com");
        coachLewis.setUniversity("StreetUniversity");
        personRepository.save(coachLewis);

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
        teamRepository.save(team2);

        Team team3 = new Team();
        team3.setName("UH-ClassZero");
        team3.setCoach(coachLewis);
        team3.setContestants(new HashSet<>(
                Arrays.asList(contestantGreg, contestantHilary,
                        contestantIsabella)));
        teamRepository.save(team3);

        Team team4 = new Team();
        team4.setName("UH-MAJA");
        team4.setCoach(coachLewis);
        team4.setContestants(new HashSet<>(
                Arrays.asList(contestantGreg, contestantHilary)));
        team4.setAttendedContest(dummyContest);
        teamRepository.save(team4);
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

    public Team registerTeamInContest(Long teamId, Long contestId)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Contest> contestOpt = contestRepository.findById(contestId);
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + contestId + " not found");
        if (!teamOpt.isPresent()) throw new NotFoundException(
                "Team with id " + teamId + " not found");

        Contest contest = contestOpt.get();
        Team team = teamOpt.get();
        validationService.validateNonFullContest(contest);
        validationService.validateTeamRegistrationInContest(team,
                contest);

        team.setAttendedContest(contest);
        teamRepository.save(team);

        return team;
    }

    public Team editTeam(TeamEditDto teamDto)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Team> teamOpt = teamRepository.findById(teamDto.getId());
        if (!teamOpt.isPresent()) throw new NotFoundException(
                "Team with id " + teamDto.getId() + " not found");
        Team team = teamOpt.get();
        mapDtoToTeam(teamDto, team);
        validationService.validateTeamEdit(team);
        teamRepository.save(team);
        return team;
    }

    public Contest editContest(ContestEditDto contestDto)
            throws NotFoundException, BusinessConstraintViolationException {
        Optional<Contest> contestOpt =
                contestRepository.findById(contestDto.getId());
        if (!contestOpt.isPresent()) throw new NotFoundException(
                "Contest with id " + contestDto.getId() + " not found");
        Contest contest = contestOpt.get();
        mapDtoToContest(contestDto, contest);
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

    private void mapDtoToTeam(TeamEditDto teamDto, Team team) {
        if (teamDto.getName() != null) team.setName(teamDto.getName());
        if (teamDto.getRank() != null) team.setRank(teamDto.getRank());
        if (teamDto.getState() != null) team.setState(teamDto.getState());
    }

    private void mapDtoToContest(ContestEditDto contestDto, Contest contest) {
        if (contestDto.getCapacity() != null) contest.setCapacity(
                contestDto.getCapacity());
        if (contestDto.getDate() != null) contest.setDate(contestDto.getDate());
        if (contestDto.getName() != null) contest.setName(contestDto.getName());
        if (contestDto.getRegistrationAllowed() != null)
            contest.setRegistrationAllowed(contestDto.getRegistrationAllowed());
        if (contestDto.getRegistrationFrom() != null)
            contest.setRegistrationFrom(contestDto.getRegistrationFrom());
        if (contestDto.getRegistrationTo() != null)
            contest.setRegistrationTo(contestDto.getRegistrationTo());
    }
}
