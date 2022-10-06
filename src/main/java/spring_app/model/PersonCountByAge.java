package spring_app.model;

import lombok.Data;

@Data
public class PersonCountByAge {
    private Integer age;
    private Long total;

    public PersonCountByAge(Integer age, Long total) {
        this.age = age;
        this.total = total;
    }

    @Override
    public String toString() {
        return this.age + " years: " + this.total;
    }
}