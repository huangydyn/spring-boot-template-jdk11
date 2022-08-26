package com.template.representation;

import com.template.base.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest extends UnitTest {

    @InjectMocks
    HelloController controller;

    @Test
    void should_get_hello_spring() throws Exception{
        assertEquals("Hello Spring 2.1 with Jdk11", controller.hello());
    }
}
