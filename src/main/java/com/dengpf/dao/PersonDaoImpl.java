package com.dengpf.dao;

import com.dengpf.bean.Person;
import com.dengpf.utils.HibernateUtils;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kobe73er on 16/10/19.
 */
@Component
public class PersonDaoImpl implements IPersonDao {

    @Autowired
    private SessionFactory sessionFactory;


//    public Person getPersonById(int id) {
//        SessionFactory sf
//                = HibernateUtils.getSessionFactory();
//        Session session = sf.openSession();
//        //每次提交会刷新缓存
//        Transaction tx = null;
//        Person person = null;
//        try {
//            tx = session.beginTransaction();
//            Query query = session.createQuery("FROM Person where id =:code");
//            query.setParameter("code", id);
//
//            List listOfResult = query.list();
//            if (listOfResult != null && listOfResult.size() != 0) {
//                person = (Person) listOfResult.get(0);
//            }
//
//
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return person;
//    }

//        public List<Person> listAllPersons () {
//            SessionFactory sf
//                    = HibernateUtils.getSessionFactory();
//            Session session = sf.openSession();
//            //每次提交会刷新缓存
//            Transaction tx = null;
//            List<Person> personList = null;
//            try {
//                tx = session.beginTransaction();
//                personList = (List<Person>) session.createQuery("FROM Person").list();
//                tx.commit();
//            } catch (HibernateException e) {
//                if (tx != null) tx.rollback();
//                e.printStackTrace();
//            } finally {
//                session.close();
//            }
//            return personList;
//        }

    public Person getPersonById(int id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    public List<Person> listAllPersons() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
        return criteria.list();
    }



}
