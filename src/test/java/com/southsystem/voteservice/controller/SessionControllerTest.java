package com.southsystem.voteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.voteservice.dto.request.SessionRequestDto;
import com.southsystem.voteservice.service.SessionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnStatusCreated_WhenPostRequestSession() throws Exception {
        SessionRequestDto sessionRequestDto = createSessionRequestDto();

        Mockito.when(sessionService.openSession(Mockito.any(), Mockito.any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/session/open/topic/{topicId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sessionRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private SessionRequestDto createSessionRequestDto() {
        SessionRequestDto sessionRequestDto = SessionRequestDto.builder().sessionTimeInMinute(5).build();
        return sessionRequestDto;
    }
}
