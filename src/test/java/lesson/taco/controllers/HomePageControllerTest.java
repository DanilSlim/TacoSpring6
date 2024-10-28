package lesson.taco.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(HomePageController.class)
public class HomePageControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") //For delete missing "Could not autowire. No beans of 'MockMvc' type found."
    @Autowired
    private MockMvc mockMVC;

    @Test
    public void testHomePage() throws Exception{

        mockMVC.perform(get("/"))       //Performs GET /
                .andExpect(status().isOk())       //Expects HTTP 200
                .andExpect(view().name("home"))   //Expects home view
                .andExpect(content().string(
                        containsString("Welcome to...")));//Expects Welcome to...


    }
}
