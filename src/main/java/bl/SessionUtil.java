package bl;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {

    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public void openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        getTransaction().commit();
        closeSession();
    }
}
