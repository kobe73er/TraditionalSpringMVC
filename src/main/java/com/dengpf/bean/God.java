package com.dengpf.bean;

import java.util.List;

/**
 * Created by kobe73er on 16/10/29.
 */
public class God {
    private List<Person> personList;

    public God(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
