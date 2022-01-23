package com.example.spring.security.apikeyauthentication.hello;

import com.example.spring.security.apikeyauthentication.constants.ConstHttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerMockMvcIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private MockMvc mockMvc;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getTest() throws Exception
    {
        this.mockMvc.perform(get("/hello").header(ConstHttpHeaders.APIKEY_HEADER, 4712)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void authenticationError() throws Exception
    {
        this.mockMvc.perform(get("/hello").header(ConstHttpHeaders.APIKEY_HEADER, "NotExisting")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    void authorizationError() throws Exception
    {
        this.mockMvc.perform(get("/hello").header(ConstHttpHeaders.APIKEY_HEADER, 4713)).andDo(print()).andExpect(status().isForbidden());
    }
}
