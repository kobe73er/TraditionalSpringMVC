package com.dengpf.controller;

import com.dengpf.bean.AngularUser;
import com.dengpf.bean.God;
import com.dengpf.bean.Person;
import com.dengpf.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobe73er on 16/10/29.
 */
@Controller
public class PersonController {

    @Autowired
    IPersonService personService;

    @RequestMapping("/getAllPersons")
    public
    @ResponseBody
    List<Person> getAllPersons() {
        return personService.listAllPersons();
    }

    @RequestMapping("/getSinglePerson")
    public
    @ResponseBody
    God getSinglePerson() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("personOne", "male"));
        personList.add(new Person("personTwo", "male"));

        return new God(personList);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public String getUser(@RequestBody AngularUser angularUser) {
        System.out.println("ID" + angularUser.getId());
        System.out.println("name" + angularUser.getName());
        System.out.println("age" + angularUser.getAge());
        return null;
    }
}
