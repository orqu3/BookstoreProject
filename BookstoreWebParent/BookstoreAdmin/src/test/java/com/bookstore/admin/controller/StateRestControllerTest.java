package com.bookstore.admin.controller;

import com.bookstore.admin.repository.CountryRepository;
import com.bookstore.admin.repository.StateRepository;
import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StateRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Test
    @WithMockUser(username = "admin", password = "something", roles = "Admin")
    public void testListByCountries() throws Exception {
        Integer countryId = 2;
        String url = "/states/list_by_country/" + countryId;

        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        State[] states = objectMapper.readValue(jsonResponse, State[].class);

        assertThat(states).hasSizeGreaterThan(1);
    }

    @Test
    @WithMockUser(username = "admin", password = "something", roles = "Admin")
    public void testCreateState() throws Exception {
        String url = "/states/save";
        Integer countryId = 2;
        Country country = countryRepository.findById(countryId).get();
        State state = new State("Arizona", country);

        MvcResult result = mockMvc.perform(post(url).contentType("application/json")
                        .content(objectMapper.writeValueAsString(state))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Integer stateId = Integer.parseInt(response);
        Optional<State> findById = stateRepository.findById(stateId);

        assertThat(findById.isPresent());
    }

    @Test
    @WithMockUser(username = "admin", password = "something", roles = "Admin")
    public void testDeleteState() throws Exception {
        Integer stateId = 6;
        String url = "/states/delete/" + stateId;

        mockMvc.perform(get(url)).andExpect(status().isOk());

        Optional<State> findById = stateRepository.findById(stateId);

        assertThat(findById).isNotPresent();
    }
}
