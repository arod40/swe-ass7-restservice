package spring_app.data;

import org.springframework.data.repository.CrudRepository;
import spring_app.model.Contest;

public interface ContestRepository extends CrudRepository<Contest, Long> {
    Contest findByName(String name);
}