package com.dengpf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kobe73er on 16/10/29.
 */
@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 如果没有匹配的页面就返回这个页面,这个可以避免404错误
     *
     * @return
     */
    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod() {
        return "CANNOT FIND THE PAGE   !";
    }
//
//    @RequestMapping("helloWorld")
//    public String helloWorld() {
//        return "helloWorld";
//    }

    @RequestMapping("helloModel")
    public ModelAndView helloModel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("helloModel");
        mav.addObject("msg", "i am a msg!");
        return mav;
    }

    @RequestMapping(value = "/methodWithRequestParam")
    @ResponseBody
    public String methodWithRequestParam(@RequestParam("id") int id) {
        logger.info("methodWithRequestParam with id= " + id);
        return "methodWithRequestParam with id= " + id;
    }

    @RequestMapping(value = "/method7/{id}")
    @ResponseBody
    public String method7(@PathVariable("id") int id) {
        return "method7 with id=" + id;
    }

    @RequestMapping(value = "/method8/{id:[\\d]+}/{name}")
    @ResponseBody
    public String method8(@PathVariable("id") long id, @PathVariable("name") String name) {
        return "method8 with id= " + id + " and name=" + name;
    }

    @RequestMapping(value = "/helloworld")
    public String helloworldProperties() {
        return "helloWorld";
    }


    @RequestMapping(value = "/helloworldPropertiesFile")
    public String helloworldPropertiesFile() {
        return "helloworldPropertiesFile";
    }

}
