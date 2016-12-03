package com.dengpf.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * Created by kobe73er on 16/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/dispatcher-servlet.xml"})
@WebAppConfiguration
public class PersonControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Ignore
    @Test
    public void testMyMvcController() throws Exception {
        ResultMatcher ok = status().isOk();
        ResultMatcher msg = MockMvcResultMatchers.model()
                .attribute("msg", "Spring quick start!!");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/getAllPersons");
        this.mockMvc.perform(builder)
                .andExpect(ok)
                .andExpect(msg);
    }

    @Test
    public void testGetPrivacyByClientSuccess() throws Exception {
        mockMvc.perform(get("/getSinglePerson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personList[0].name", containsString("personOne")))
                .andExpect(jsonPath("$.personList",hasSize(2)))
                .andExpect(jsonPath("$.personList[0].name", notNullValue()))
//                .andExpect(jsonPath("$.settingText", notNullValue()))
//                .andExpect(jsonPath("$.value", containsString("test")))
//                .andExpect(jsonPath("$.exception", nullValue()))
                .andDo(print());
    }
}