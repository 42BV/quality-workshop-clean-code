package nl._42.qualityws.cleancode.collectors_item;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl._42.qualityws.cleancode.collector.Collector;
import nl._42.qualityws.cleancode.collector.builder.CollectorBuilder;
import nl._42.qualityws.cleancode.collectors_item.builder.CollectorsItemBuilder;
import nl._42.qualityws.cleancode.shared.AbstractWebIntegrationTest;

public class CollectorsItemControllerTest extends AbstractWebIntegrationTest {

    @Autowired
    private CollectorsItemBuilder itemBuilder;
    @Autowired
    private CollectorBuilder collectorBuilder;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void listMovies_shouldSucceed_whenFirstPageIsRequested() throws Exception {
        Collector collector = collectorBuilder.collector("Jan de Vries").save();
        itemBuilder.movie("The Wire", collector).withImdbUrl("http://www.imdb.com/title/tt0306414/").save();
        itemBuilder.movie("John Wick: chapter 2", collector).withImdbUrl("http://www.imdb.com/title/tt4425200/").save();

        webClient.perform(get("/items/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content[0].collector.name").value("Jan de Vries"))
                .andExpect(jsonPath("$.content[0].name").value("John Wick: chapter 2"))
                .andExpect(jsonPath("$.content[0].imdbUrl").value("http://www.imdb.com/title/tt4425200/"));
    }

    @Test
    public void createMovie_shouldSucceed_whenValidFormIsPosted() throws Exception {
        Collector collector = collectorBuilder.collector("Jan de Vries").save();
        MovieForm form = new MovieForm();
        form.collector = collector.getId();
        form.name = "The Wire";
        form.imdbUrl = "http://www.imdb.com/title/tt4425200/";

        webClient.perform(MockMvcRequestBuilders.post("/items/movies")
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.collector.name").value("Jan de Vries"))
                .andExpect(jsonPath("$.name").value("The Wire"))
                .andExpect(jsonPath("$.imdbUrl").value("http://www.imdb.com/title/tt4425200/"));
    }
    
    @Test
    public void createMovie_shouldFail_whenInvalidFormIsPosted() throws Exception {
        webClient.perform(MockMvcRequestBuilders.post("/items/movies")
                .content(objectMapper.writeValueAsString(new MovieForm())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.fieldErrors").isArray())
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].field").value("name"))
                .andExpect(jsonPath("$.fieldErrors[0].code").value("NotEmpty"));
    }
}
