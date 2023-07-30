package com.test.cafekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cafekiosk.spring.api.controller.order.OrderController;
import com.test.cafekiosk.spring.api.controller.product.ProductController;
import com.test.cafekiosk.spring.api.service.order.OrderService;
import com.test.cafekiosk.spring.api.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;

    /**
     * Controller 에 ProductService MockBean 객체를 주입해줌
     */
    @MockBean
    protected ProductService productService;
}
