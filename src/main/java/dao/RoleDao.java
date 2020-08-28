package dao;

import entities.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    //create
    void add(Role role) throws SQLException;

    //read
    List<Role> getAll() throws SQLException;

    //read by id
    Role getById(int id) throws SQLException;

    //update
    void update(Role role) throws SQLException;

    //delete
    void remove(Role role) throws SQLException;
}
