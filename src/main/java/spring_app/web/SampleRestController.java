package spring_app.web;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_app.model.Contest;
import spring_app.model.Person;
import spring_app.model.PersonCountByAge;
import spring_app.model.Team;
import spring_app.service.BusinessConstraintViolationException;
import spring_app.service.SampleService;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@RestController
public class SampleRestController {

    @Autowired
    SampleService service;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Business Constraint Violation")
    @ExceptionHandler(BusinessConstraintViolationException.class)
    public void handleBusinessConstraintViolationException() {
        // Nothing to do
    }

    @GetMapping("/team/all")
    public List<Team> getTeams() {
        return service.findAllTeams();
    }

    @GetMapping("/contest/all")
    public List<Contest> getContests() {
        return service.findAllContests();
    }

    @GetMapping("/person/all")
    public List<Person> getPeople() {
        return service.findAllPeople();
    }


    @PostMapping("/populate")
    public ResponseEntity<String> populate() throws ParseException {
        service.populate();
        return new ResponseEntity<>("DB succesfully populated", HttpStatus.OK);
    }

    @GetMapping("/team")
    public List<Team> getTeamsInContest(
            @RequestParam Long contestId) throws NotFoundException {
        return service.getTeamsInContest(contestId);
    }

    @GetMapping("/team/age")
    public List<PersonCountByAge> getTeamMembersAgeReport() {
        return service.getTeamMembersAgeReport();
    }

    @GetMapping("/contest/occupancy")
    public String getContestOccupancyReport(
            @RequestParam int contestId) throws NotFoundException {
        return service.getContestOccupancyReport(Long.valueOf(contestId));
    }

    @PostMapping("/contest/registration")
    public Team registerInContest(@RequestBody Team team,
                                  @RequestParam Long contestId)
            throws NotFoundException, BusinessConstraintViolationException {
        return service.registerTeamInContest(team, contestId);
    }

    @PatchMapping("/team/edit")
    public Team editTeam(@RequestBody Team team)
            throws BusinessConstraintViolationException, NotFoundException {
        return service.editTeam(team);
    }

    @PatchMapping("/contest/edit")
    public Contest editContest(@RequestBody Contest contest)
            throws BusinessConstraintViolationException, NotFoundException {
        return service.editContest(contest);
    }

    @PostMapping("/contest/mark_edit")
    public Contest setContestEditable(@RequestParam Long contestId)
            throws NotFoundException {
        return service.setContestEditable(contestId, true);
    }

    @PostMapping("/contest/mark_readonly")
    public Contest setContestReadonly(@RequestParam Long contestId)
            throws NotFoundException {
        return service.setContestEditable(contestId, false);
    }

    @PostMapping("/team/promote")
    public Team promoteTeam(@RequestParam Long teamId,
                            @RequestParam Long superContestId)
            throws NotFoundException, BusinessConstraintViolationException {
        return service.promoteTeam(teamId, superContestId);
    }
}
