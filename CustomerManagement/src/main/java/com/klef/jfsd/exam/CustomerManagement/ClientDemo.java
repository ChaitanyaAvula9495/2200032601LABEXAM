package com.klef.jfsd.exam.CustomerManagement;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Insert records
            Customer c1 = new Customer();
            c1.setName("Alice");
            c1.setEmail("alice@example.com");
            c1.setAge(25);
            c1.setLocation("New York");

            Customer c2 = new Customer();
            c2.setName("Bob");
            c2.setEmail("bob@example.com");
            c2.setAge(30);
            c2.setLocation("Los Angeles");

            session.save(c1);
            session.save(c2);
            transaction.commit();

            // Criteria Queries
            System.out.println("Criteria Queries Results:");

            // 1. Equal
            List<Customer> customersEqual = session.createCriteria(Customer.class)
                    .add(Restrictions.eq("location", "New York")).list();
            System.out.println("Location = New York: " + customersEqual);

            // 2. Not Equal
            List<Customer> customersNotEqual = session.createCriteria(Customer.class)
                    .add(Restrictions.ne("location", "New York")).list();
            System.out.println("Location != New York: " + customersNotEqual);

            // 3. Less Than
            List<Customer> customersLessThan = session.createCriteria(Customer.class)
                    .add(Restrictions.lt("age", 30)).list();
            System.out.println("Age < 30: " + customersLessThan);

            // 4. Greater Than
            List<Customer> customersGreaterThan = session.createCriteria(Customer.class)
                    .add(Restrictions.gt("age", 25)).list();
            System.out.println("Age > 25: " + customersGreaterThan);

            // 5. Between
            List<Customer> customersBetween = session.createCriteria(Customer.class)
                    .add(Restrictions.between("age", 20, 30)).list();
            System.out.println("Age between 20 and 30: " + customersBetween);

            // 6. Like
            List<Customer> customersLike = session.createCriteria(Customer.class)
                    .add(Restrictions.like("name", "A%")).list();
            System.out.println("Name like 'A%': " + customersLike);

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
