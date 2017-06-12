package nl._42.qualityws.cleancode.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl._42.qualityws.cleancode.model.Collector;
import nl._42.qualityws.cleancode.shared.AbstractWebIntegrationTest;
import nl._42.qualityws.cleancode.shared.test.builder.CollectorBuilder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectorControllerTest extends AbstractWebIntegrationTest {
    
    @Autowired
    private CollectorBuilder collectorBuilder;
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void list() throws Exception {
        collectorBuilder.collector("Yvonne IJzer").save();
        collectorBuilder.collector("Ahmar Warraq").save();
        
        webClient.perform(get("/collectors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content[0].name").value("Ahmar Warraq"))
                .andExpect(jsonPath("$.content[1].name").value("Yvonne IJzer"));
    }
    
    @Test
    public void create() throws Exception {
        Collector collector = new Collector();
        collector.setName("Jan de Vries");

        webClient.perform(MockMvcRequestBuilders.post("/collectors")
                .content(objectMapper.writeValueAsString(collector)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jan de Vries"));
    }
    
    @Test
    public void fail() throws Exception {
        webClient.perform(MockMvcRequestBuilders.post("/collectors")
                .content(objectMapper.writeValueAsString(new Collector())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.fieldErrors").isArray())
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].field").value("name"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("NotEmpty"));
    }
}
