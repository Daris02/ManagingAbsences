package hei.server.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Student implements Serializable {
    private Integer id;
    private String refStudent;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Boolean active;
    private Integer idGroup;
}
