package spring_app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContestEditDto {
    private Long id;

    private Integer capacity;

    private Date date;

    private String name;

    private Boolean registrationAllowed;

    private Date registrationFrom;

    private Date registrationTo;
}
