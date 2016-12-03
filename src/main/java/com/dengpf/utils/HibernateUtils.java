package com.dengpf.utils;

import com.dengpf.bean.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kobe73er on 16/10/19.
 */
public class HibernateUtils {

    private  Logger logger = LoggerFactory.getLogger(getClass());

    private static SessionFactory factory;

    private static SessionFactory annotatedFactory;


    public static SessionFactory getSessionFactory() {
        try {
            if (factory == null) {
                factory = new Configuration().configure().buildSessionFactory();
            }
            return factory;
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

//    public static SessionFactory getAnnotatedSessonFactory() {
//        try {
//            annotatedFactory = new AnnotationConfiguration().
//                    configure().
//                    //addPackage("com.xyz") //add package if used.
//                            addAnnotatedClass(Student.class).
//                            buildSessionFactory();
//        } catch (Throwable ex) {
//            logger.info("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

}
