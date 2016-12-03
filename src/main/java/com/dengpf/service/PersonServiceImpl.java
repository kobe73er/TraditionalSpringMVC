package com.dengpf.service;

import com.dengpf.Exception.ObjectNotFoundException;
import com.dengpf.bean.Person;
import com.dengpf.dao.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kobe73er on 16/10/19.
 */
@Component
public class PersonServiceImpl implements IPersonService {
    @Autowired
    IPersonDao personDao;

    @Transactional
    public Person getPersonById(int id) {
        Person person = personDao.getPersonById(id);
        if(null == person){
            throw new ObjectNotFoundException(id);
        }
        return person;
    }

    @Transactional
    public List<Person> listAllPersons() {
        return personDao.listAllPersons();
    }
}
