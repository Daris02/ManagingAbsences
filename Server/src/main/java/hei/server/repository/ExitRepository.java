package hei.server.repository;

import hei.server.model.Exit;
import hei.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExitRepository extends JpaRepository<Exit, Integer> {
    /**
    String sql = "INSERT INTO "+ type +" (id_student) VALUES (" + id + "); ";
     */
    void addExit(Integer id);

    /**
    String sql = "SELECT " +
            "    st.* " +
            "FROM \"student\" st " +
            "    INNER JOIN \"entry\" en ON st.id = en.id_student " +
            "    FULL OUTER JOIN \"exit\" ex ON st.id = ex.id_student " +
            "WHERE " +
            "    ((st.id IN (" +
            "        SELECT entry.id_student FROM entry" +
            "    )) AND (date_part('hour', ex.date) >= date_part('hour', en.date) + 2))" +
            "    AND date_part('year', '" + datetime + "'::TIMESTAMP) = date_part('year', en.date)" +
            "    AND date_part('month', '" + datetime + "'::TIMESTAMP) = date_part('month', en.date)" +
            "    AND date_part('day', '" + datetime + "'::TIMESTAMP) = date_part('day', en.date)" +
            "    ;"; */
    List<Student> getAllStudentsPresent(String datetime);

    /**
    String sql = "SELECT" +
            "    st.* " +
            "FROM \"student\" st " +
            "WHERE " +
            "    st.id NOT IN (" +
            "        SELECT" +
            "            st.id" +
            "        FROM \"student\" st" +
            "            INNER JOIN \"entry\" en ON st.id = en.id_student " +
            "        WHERE date_part('year', en.date) = date_part('year', '" + datetime + "'::TIMESTAMP)" +
            "        AND date_part('month', en.date) = date_part('month', '" + datetime + "'::TIMESTAMP)" +
            "        AND date_part('day', en.date) = date_part('day', '" + datetime + "'::TIMESTAMP) )" +
            "OR " +
            "    st.id IN (" +
            "        SELECT" +
            "            st.id" +
            "        FROM \"student\" st" +
            "            INNER JOIN \"entry\" en ON st.id = en.id_student " +
            "            INNER JOIN \"exit\" ex ON st.id = ex.id_student " +
            "        WHERE date_part('year', en.date) = date_part('year', '" + datetime + "'::TIMESTAMP)" +
            "        AND date_part('month', en.date) = date_part('month', '" + datetime + "'::TIMESTAMP)" +
            "        AND date_part('day', en.date) = date_part('day', '" + datetime + "'::TIMESTAMP)" +
            "        AND (date_part('hour', ex.date) < date_part('hour', en.date) + 2)" +
            "  );"; */
    List<Student> getAllStudentsAbsent(String datetime);
}
