package com.grocery.helper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grocery.helper.model.Grocery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GroceryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String BASE_URL = "/api/v1";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void test_getGroceryData_success() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/groceries")).andExpect(status().isOk());
    }

    @Test
    public void test_saveGroceryData_success() throws Exception {

        Grocery grocery = new Grocery(1, "Oil", 10, "Cooking Oil");
        String requestJson = getMapper(grocery);

        this.mockMvc.perform(post(BASE_URL + "/add-grocery").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void test_saveGroceryData_fail() throws Exception {
        this.mockMvc.perform(post(BASE_URL + "/add-grocery")).andExpect(status().is4xxClientError());
    }

    @Test
    public void test_updateGroceryData_success() throws Exception {

        Grocery grocery = new Grocery(1, "Oil", 10, "Cooking Oil");
        String requestJson = getMapper(grocery);

        this.mockMvc.perform(put(BASE_URL + "/grocery/1").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateGroceryData_fail() throws Exception {

        Grocery grocery = new Grocery(11, "Oil", 10, "Cooking Oil");
        String requestJson = getMapper(grocery);

        this.mockMvc.perform(put(BASE_URL + "/grocery/11").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test_deleteGroceryData_success() throws Exception {

        Grocery grocery = new Grocery(1, "Oil", 10, "Cooking Oil");
        String requestJson = getMapper(grocery);

        this.mockMvc.perform(put(BASE_URL + "/grocery/1").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteGroceryData_fail() throws Exception {

        Grocery grocery = new Grocery(11, "Oil", 10, "Cooking Oil");
        String requestJson = getMapper(grocery);

        this.mockMvc.perform(delete(BASE_URL + "/grocery/11").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is4xxClientError());
    }

    private String getMapper(Grocery grocery) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(grocery);
    }
}