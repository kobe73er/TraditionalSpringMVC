package com.dengpf.service;


import com.dengpf.bean.Person;

import java.util.List;

/**
 * Created by kobe73er on 16/10/19.
 */
public interface IPersonService {
    Person getPersonById(int id);

    List<Person> listAllPersons();
}
