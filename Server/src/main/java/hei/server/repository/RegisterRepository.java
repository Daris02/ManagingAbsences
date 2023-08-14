package hei.server.repository;

import hei.server.DataBase.StatementDB;
import hei.server.model.Register;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegisterRepository {
    public List<Register> getAll(String type) {
        String sql = "SELECT * FROM "+ type +" ORDER BY date ;";

        List<Register> allRegisters = new ArrayList<>(0);

        try {
            ResultSet resultSet = StatementDB.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allRegisters.add(new Register(
                        resultSet.getInt("id"),
                        resultSet.getDate("date").toLocalDate(),
                    resultSet.getInt("id_student")
                ));
            }
            return allRegisters;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
