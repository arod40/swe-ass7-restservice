package spring_app.data;

import org.springframework.data.repository.CrudRepository;
import spring_app.model.Contest;
import spring_app.model.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
}