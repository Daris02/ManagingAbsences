package hei.server.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Register implements Serializable {
    private Integer id;
    private LocalDate date;
    private Integer idStudent;
}
