package ir.ac.ut.ie.Bolbolestan06.repository.ClassTime;

import ir.ac.ut.ie.Bolbolestan06.utils.Pair;
import ir.ac.ut.ie.Bolbolestan06.controllers.domain.Bolbolestan.Offering.ClassTime;
import ir.ac.ut.ie.Bolbolestan06.repository.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface IClassTimeMapper extends IMapper<ClassTime, Pair> {
    List<ClassTime> getAll() throws SQLException;
}
