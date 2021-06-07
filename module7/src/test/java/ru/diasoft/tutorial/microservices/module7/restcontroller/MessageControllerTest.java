package ru.diasoft.tutorial.microservices.module7.restcontroller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.diasoft.tutorial.microservices.module7.entity.Message;
import ru.diasoft.tutorial.microservices.module7.service.MessageService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService service;


    @Test
    public void webMvcPostTest() throws Exception {
        given(service.create(ArgumentMatchers.any())).willReturn(new Message(1L, "Test message from mock"));
        mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON).content("{\"text\":\"Test message from mock\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", is("Test message from mock")))

        ;
    }

    @Test
    public void webMvcPutTest() throws Exception {
        given(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).willReturn(new Message(2L, "Test message from mock"));
        mockMvc.perform(put("/messages/2")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\": \"2\", \"text\":\"Test message from mock\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", is("Test message from mock")));
    }

    @Test
    public void webMvcGetTest() throws Exception {
        given(service.findByID(ArgumentMatchers.anyLong())).willReturn(new Message(3L, "Test message from mock"));
        mockMvc.perform(get("/messages/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.text", is("Test message from mock")));

    }

    @Test
    public void webMvcGetAllTest() throws Exception {
        Message message1 = new Message(1L, "Test message from mock 1");
        Message message2 = new Message(2L, "Test message from mock 2");
        List<Message> mList = new ArrayList<>();
        mList.add(message1);
        mList.add(message2);
        given(service.findAll()).willReturn(mList);
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
        ;
    }

    @Test
    public void deleteById() throws Exception {
        given(service.delete(1L)).willReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/messages/1"))
                .andExpect(status().isOk());
    }

}
