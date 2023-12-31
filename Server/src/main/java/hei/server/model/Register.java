package hei.server.model;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Register implements Serializable {
    private Integer id;
    private Timestamp date;
    private Integer idStudent;
}
