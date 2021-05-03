package ir.ac.ut.ie.Bolbolestan06.repository.Grade;

import ir.ac.ut.ie.Bolbolestan06.Utils.Pair;
import ir.ac.ut.ie.Bolbolestan06.Utils.Utils;
import ir.ac.ut.ie.Bolbolestan06.controllers.domain.Bolbolestan.Offering.Offering;
import ir.ac.ut.ie.Bolbolestan06.controllers.domain.Bolbolestan.Student.Grade;
import ir.ac.ut.ie.Bolbolestan06.repository.ConnectionPool;
import ir.ac.ut.ie.Bolbolestan06.repository.Mapper;
import ir.ac.ut.ie.Bolbolestan06.repository.Prerequisite.IPrerequisiteMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GradeMapper extends Mapper<Grade, Pair> implements IGradeMapper { // returns Grade objects which do not have the course name and
    // should be set later via the getCourseNameByCourseCode function

    private static final String COLUMNS = "studentId, courseCode, grade, term";
    private static final String TABLE_NAME = "GRADES";

    public GradeMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
//            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "CREATE TABLE IF NOT EXISTS %s (\n" +
                            "    studentId varchar(255) not null,\n" +
                            "    courseCode varchar(255) not null,\n" +
                            "    grade int,\n" +
                            "    term int not null,\n" +
                            "    primary key(studentId, courseCode, term),\n" +
                            "    foreign key (courseCode) references COURSES(code)\n" +
                            ");",
                    TABLE_NAME));
            st.execute(String.format("ALTER TABLE %s CHARACTER SET utf8 COLLATE utf8_general_ci;", TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public GradeMapper() throws SQLException {
    }

    @Override
    protected String getFindStatement(Pair pair) {
        String studentId = pair.getArgs().get(0);
        return String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "studentId", Utils.quoteWrapper(studentId));
    }


    @Override
    protected String getInsertStatement(Grade grade) {
        return String.format("INSERT IGNORE INTO %s ( %s ) values (%s, %s, %d, %s);", TABLE_NAME, COLUMNS,
                Utils.quoteWrapper(grade.getStudentId()), Utils.quoteWrapper(grade.getCode()),
                grade.getGrade(), grade.getTerm());
    }

    @Override
    protected String getDeleteStatement(Pair pair) {
        String studentId = pair.getArgs().get(0);
        return String.format("delete from %s where %s.%s = %s", TABLE_NAME, TABLE_NAME, "studentId", Utils.quoteWrapper(studentId));
    }

    @Override
    protected Grade convertResultSetToObject(ResultSet rs) throws SQLException {
        return new Grade(
                rs.getString("studentId"),
                rs.getString("courseCode"),
                rs.getInt("grade"),
                rs.getInt("term")
        );
    }


    private ArrayList<Grade> getStudentGrades(String studentId) throws SQLException {
        ArrayList<Grade> result = new ArrayList<>();
        String statement = String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "studentId", Utils.quoteWrapper(studentId));
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add(convertResultSetToObject(resultSet));
                con.close();
                return result;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.getStudentGrades query.");
                throw ex;
            }
        }
    }

    /*@Override
    protected HashMap<String, ArrayList<String>> convertResultSetToObject(ResultSet rs) throws SQLException {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        ArrayList<String> courses = new ArrayList<>();
        String courseCode = "";
        while (rs.next()) {
            String correspondingCourseCode = rs.getString("courseCode");
            if(!courseCode.equals("")) {
                if(!correspondingCourseCode.equals(courseCode)) System.out.println("something went wring in prerequisites");
                assert correspondingCourseCode.equals(courseCode);
            }
            else
                courseCode = correspondingCourseCode;

            String prerequisiteCode = rs.getString("prerequisiteCode");
            courses.add(prerequisiteCode);
        }
        result.put(courseCode, courses);
        return result;
    }*/
}