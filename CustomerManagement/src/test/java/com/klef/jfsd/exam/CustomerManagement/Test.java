package com.klef.jfsd.exam.CustomerManagement;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Open Hibernate Session
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Insert a new customer
        insertCustomer(session);

        // Fetch all customers
        fetchAllCustomers(session);

        // Apply Criteria Queries
        applyCriteriaQueries(session);

        // Close the session
        session.close();
    }

    private static void insertCustomer(Session session) {
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setAge(28);
        customer.setLocation("California");

        session.save(customer);
        transaction.commit();
        System.out.println("Customer inserted successfully!");
    }

    private static void fetchAllCustomers(Session session) {
        List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
        System.out.println("List of Customers:");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getId() +
                               ", Name: " + customer.getName() +
                               ", Email: " + customer.getEmail() +
                               ", Age: " + customer.getAge() +
                               ", Location: " + customer.getLocation());
        }
    }

    private static void applyCriteriaQueries(Session session) {
        System.out.println("Applying Criteria Queries:");

        // Example: Customers aged between 20 and 30
        List<Customer> customers = session.createQuery("from Customer where age between 20 and 30", Customer.class).list();
        System.out.println("Customers aged between 20 and 30:");
        for (Customer customer : customers) {
            System.out.println(customer.getName());
        }
    }
}
