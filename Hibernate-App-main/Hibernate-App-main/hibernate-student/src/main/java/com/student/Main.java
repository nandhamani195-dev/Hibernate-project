package com.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // HIBERNATE CONFIGURATION
        // ==========================================

        Configuration configuration = new Configuration();

        // Database Driver
        configuration.setProperty(
                "hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver"
        );

        // Database URL
        configuration.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://db01.dbhost.dev:5051/db_455a294mn"
        );

        // Database Username
        configuration.setProperty(
                "hibernate.connection.username",
                "user_455a294mn"
        );

        // Database Password
        configuration.setProperty(
                "hibernate.connection.password",
                "p455a294mn"
        );

        // Hibernate Settings
        configuration.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );

        configuration.setProperty(
                "hibernate.show_sql",
                "true"
        );

        configuration.setProperty(
                "hibernate.format_sql",
                "true"
        );

        configuration.setProperty(
                "hibernate.hbm2ddl.auto",
                "update"
        );

        // Add Student Entity
        configuration.addAnnotatedClass(Student.class);

        // Create SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // ==========================================
        // OPEN SESSION
        // ==========================================

        Session session = factory.openSession();

        try {

            session.beginTransaction();

            // Check whether Student ID 1 already exists
            Student student = session.get(Student.class, 1);

            if (student == null) {

                // ==========================================
                // INSERT
                // ==========================================

                student = new Student(
                        1,
                        "nandhakumaran M",
                        "nandhamani195@gmail.com",
                        "AI & Data Science"
                );

                session.persist(student);

                System.out.println("=================================");
                System.out.println("Student inserted successfully!");
                System.out.println(student);
                System.out.println("=================================");

            } else {

                System.out.println("Student already exists.");

            }

            session.getTransaction().commit();

        } catch (Exception e) {

            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            session.close();
            factory.close();
        }


        // ==========================================
        // UPDATE STUDENT
        // ==========================================

        Configuration updateConfiguration = new Configuration();

        updateConfiguration.setProperty(
                "hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver"
        );

        updateConfiguration.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://db01.dbhost.dev:5051/db_455a294mn"
        );

        updateConfiguration.setProperty(
                "hibernate.connection.username",
                "user_455a294mn"
        );

        updateConfiguration.setProperty(
                "hibernate.connection.password",
                "p455a294mn"
        );

        updateConfiguration.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );

        updateConfiguration.setProperty(
                "hibernate.show_sql",
                "true"
        );

        updateConfiguration.setProperty(
                "hibernate.format_sql",
                "true"
        );

        updateConfiguration.setProperty(
                "hibernate.hbm2ddl.auto",
                "update"
        );

        updateConfiguration.addAnnotatedClass(Student.class);

        SessionFactory updateFactory =
                updateConfiguration.buildSessionFactory();

        Session updateSession = updateFactory.openSession();

        try {

            updateSession.beginTransaction();

            // Find Student
            Student student =
                    updateSession.get(Student.class, 1);

            if (student != null) {

                // Update details
                student.setName("nandhakumaran M");
                student.setEmail("nandhamani195@gmail.com");
                student.setCourse("Artificial Intelligence");

                System.out.println("=================================");
                System.out.println("Student updated successfully!");
                System.out.println("Updated Student:");
                System.out.println(student);
                System.out.println("=================================");
            }

            updateSession.getTransaction().commit();

        } catch (Exception e) {

            if (updateSession.getTransaction().isActive()) {
                updateSession.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            updateSession.close();
            updateFactory.close();
        }
    }
}