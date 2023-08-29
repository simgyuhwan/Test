package com.openapi.restdoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openapi.restdoc.controller.ItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Created by Gyuhwan
 */
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WebMvcTest(ItemController.class)
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    ResultActions resultActions;

    String json;

}
