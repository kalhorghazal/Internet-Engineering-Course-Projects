package ir.ac.ut.ie.Bolbolestan06.repository;

import java.sql.SQLException;

public interface IMapper<T, I> {
    T find(I id) throws SQLException;
    void insert(T t) throws SQLException;
    void delete(I id) throws SQLException;
}