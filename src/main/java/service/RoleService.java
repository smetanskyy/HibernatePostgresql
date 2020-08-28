package service;

import bl.SessionUtil;
import dao.RoleDao;
import entities.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoleService extends SessionUtil implements RoleDao {

    public void add(Role role) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(role);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<Role> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();
        Session session = getSession();

        //Query query = session.createQuery("from Role");

        String sql = "SELECT * FROM tblroles";
        Query query = session.createNativeQuery(sql).addEntity(Role.class);

        List<Role> rolesList = query.list();

        //close session with a transaction
        closeTransactionSession();
        return rolesList;
    }

    public Role getById(int id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        Role role = (Role) session.get(Role.class, id);

        // String sql = "SELECT * FROM tblroles WHERE ID = :id";
        // Query query = session.createNativeQuery(sql).addEntity(Role.class);
        // query.setParameter("id", id);
        // Role role = (Role) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return role;
    }

    public void update(Role role) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(role);

        //close session with a transaction
        closeTransactionSession();
    }

    public void remove(Role role) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(role);

        //close session with a transaction
        closeTransactionSession();
    }
}
