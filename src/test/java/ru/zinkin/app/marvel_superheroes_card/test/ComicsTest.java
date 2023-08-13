package ru.zinkin.app.marvel_superheroes_card.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ComicsTest  {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void checkAllComicsAPI() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/public/comics"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void checkFindComicsById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/public/comics/tt3-4s-32"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("tees"))
                .andExpect(jsonPath("$.description").value("description t-e-e-s"));
    }
}
