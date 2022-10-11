package spring_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_app.data.ContestRepository;
import spring_app.data.TeamRepository;
import spring_app.model.Contest;
import spring_app.model.Team;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BusinessRulesValidationService {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TeamRepository teamRepository;

    public void validateContestIsEditable(Contest contest)
            throws BusinessConstraintViolationException {
        if (!contest.isEditable())
            throw new BusinessConstraintViolationException(
                    "The contest must be marked editable.");
    }

    public void validateNonFullContest(Contest contest)
            throws BusinessConstraintViolationException {
        if (contest.getTeams().size() == contest.getCapacity())
            throw new BusinessConstraintViolationException(
                    "A contest must have free spots to register a new team.");
    }

    public void validateTeamRegistrationInContest(Team team, Contest contest)
            throws BusinessConstraintViolationException {
        validateContestIsEditable(contest);
        if (team.getCoach() == null)
            throw new BusinessConstraintViolationException(
                    "A team must have a coach in order to be registered.");
        if (team.getContestants().size() != 3)
            throw new BusinessConstraintViolationException(
                    "A team must have three members in order to be registered.");
        if (team.getContestants().stream().distinct().toList().size() !=
                team.getContestants().size() || team.getContestants().stream()
                .anyMatch(x -> x == team.getCoach()))
            throw new BusinessConstraintViolationException(
                    "A team's members and coach should be distinct people in order to be registered.");
        if (team.getContestants().stream().anyMatch(x -> x.getAge() >= 24))
            throw new BusinessConstraintViolationException(
                    "A team members' age should be less than 24 years in order to register that team.");
        if (contest.getTeams().stream().anyMatch(
                x -> x.getContestants().stream().anyMatch(
                        y -> team.getContestants().stream()
                                .anyMatch(z -> z == y))))
            throw new BusinessConstraintViolationException(
                    "A contest should not have teams sharing team members.");
    }

    public void validateTeamEdit(Team team)
            throws BusinessConstraintViolationException {

        if (team.getAttendedContest() != null) {
            validateContestIsEditable(team.getAttendedContest());
            Contest contest = team.getAttendedContest();
            Team inContest =
                    contest.getTeams().stream().filter(x -> x.getId() ==
                                    team.getId()).findFirst()
                            .orElse(null); // this will never happen, tho
            Set<Team> newTeams = new HashSet<>();
            for (Team x : contest.getTeams())
                if (x != inContest) newTeams.add(x);
            contest.setTeams(newTeams);
            validateTeamRegistrationInContest(team, contest);
        }

    }

    public void validateContestEdit(Contest contest)
            throws BusinessConstraintViolationException {
        validateContestIsEditable(contest);
        if (contest.getCapacity() < contest.getTeams().size())
            throw new BusinessConstraintViolationException(
                    "The new capacity of the contest must be greater than or equal the current number of registered teams.");
    }

    public void validateTeamPromotionToSuperContest(Team team, Contest contest)
            throws BusinessConstraintViolationException {
        validateContestIsEditable(contest);
        validateTeamRegistrationInContest(team, contest);

        if (contest.getPreliminaryRound() != team.getAttendedContest())
            throw new BusinessConstraintViolationException(
                    "A team must be cloned from a preliminary round to a subsequent round.");
        if (team.getRank() == null || team.getRank() > 5)
            throw new BusinessConstraintViolationException(
                    "To promote a team it must have a rank between 1-5");
    }
}

