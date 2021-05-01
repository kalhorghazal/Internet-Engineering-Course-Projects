package ir.ac.ut.ie.Bolbolestan05.repository.ExamTime;

import ir.ac.ut.ie.Bolbolestan05.Utils.Pair;
import ir.ac.ut.ie.Bolbolestan05.Utils.Utils;
import ir.ac.ut.ie.Bolbolestan05.controllers.domain.Bolbolestan.Offering.ExamTime;
import ir.ac.ut.ie.Bolbolestan05.repository.ConnectionPool;
import ir.ac.ut.ie.Bolbolestan05.repository.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamTimeMapper extends Mapper<ExamTime, String> implements IExamTimeMapper {

    private static final String COLUMNS = " time ";
    private static final String TABLE_NAME = "EXAM_TIMES";

    public ExamTimeMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    start text,\n" +
                            "    end text,\n" +
                            "    name varchar(255) not null,\n" +
                            "    primary key(start, end)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public ExamTimeMapper() throws SQLException {}

    @Override
    protected String getFindStatement(String id) {
        return null;
    }

    @Override
    protected String getInsertStatement(ExamTime examTime) {
        return String.format("INSERT INTO %s ( %s ) values (%s, %s);", TABLE_NAME, COLUMNS,
                Utils.quoteWrapper(examTime.getStart()), Utils.quoteWrapper(examTime.getEnd()));
    }

    @Override
    protected String getDeleteStatement(String id) {
        return null;
    }

    protected String getDeleteStatement(Pair id) {
        return String.format("delete from %s where %s.%s = %s and %s.%s = %s", TABLE_NAME, TABLE_NAME, "restaurantId",
                Utils.quoteWrapper(id.getArgs().get(0)), TABLE_NAME, "name", Utils.quoteWrapper(id.getArgs().get(1)));
    }

    @Override
    protected ExamTime convertResultSetToObject(ResultSet rs) throws SQLException {
        return new ExamTime(
                rs.getString("start"),
                rs.getString("end")
        );
    }

    @Override
    public List<ExamTime> getAll() throws SQLException {
        List<ExamTime> result = new ArrayList<ExamTime>();
        String statement = "SELECT * FROM " + TABLE_NAME;
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add(convertResultSetToObject(resultSet));
                return result;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findAll query.");
                throw ex;
            }
        }
    }
}