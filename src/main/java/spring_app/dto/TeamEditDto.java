package spring_app.dto;

import lombok.Data;
import spring_app.model.State;

@Data
public class TeamEditDto {
    private Long id;

    private String name;

    private Integer rank;

    private State state;
}
