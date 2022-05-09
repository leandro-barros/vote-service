package com.southsystem.voteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResultDto;
import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.service.VoteService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class VoteControllerTest {

    private static final String URL_VOTE = "/api/v1/vote/topic";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnStatusOk_WhenGetRequestFindResultVote() throws Exception {
        VoteResultDto voteResultDto = createVoteResult();

        Mockito.when(voteService.result(Mockito.any())).thenReturn(voteResultDto);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_VOTE+ "/{topicId}/result", 1))
                .andExpect(jsonPath("$.voteInFavor").value(20))
                .andExpect(jsonPath("$.result").value("Aprovada"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnStatusCreated_WhenPostRequestVote() throws Exception {
        VoteRequestDto voteRequestDto = createVoteRequestDto();

        Mockito.when(voteService.saveVote(Mockito.any(), Mockito.any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post(URL_VOTE+ "/{topicId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private VoteResultDto createVoteResult() {
        VoteResultDto voteResultDto = VoteResultDto.builder()
                                        .voteInFavor(20L)
                                        .voteAgainst(16L)
                                        .topic("Topic 1")
                                        .result("Aprovada")
                                    .build();

        return voteResultDto;
    }

    private VoteRequestDto createVoteRequestDto() {
        Associate associate = new Associate(1L, "Leandro", "12365958605");
        VoteRequestDto voteRequestDto = VoteRequestDto.builder().associate(associate).vote(true).build();
        return voteRequestDto;
    }
}
