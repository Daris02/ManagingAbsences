package hei.server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "entry")
public class Entry extends Register {
    public Entry(Student student) {
        this.setStudent(student);
        this.setDate(new Timestamp(System.currentTimeMillis()));
    }
}
