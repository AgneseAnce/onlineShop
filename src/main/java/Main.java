import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import product.Product;
import org.hibernate.cfg.Configuration;


public class Main {

    SessionFactory sessionFactory;

    public static void start(String[] args) {
        Main main = new Main();

        main.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product();

        session.persist(product);

        transaction.commit();

        session.close();
    }
}
