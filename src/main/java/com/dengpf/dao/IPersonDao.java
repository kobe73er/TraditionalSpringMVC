package com.dengpf.dao;


import com.dengpf.bean.Person;

import java.util.List;

/**
 * Created by kobe73er on 16/10/19.
 */
public interface IPersonDao {
    Person getPersonById(int id);

    public List<Person> listAllPersons();
}
