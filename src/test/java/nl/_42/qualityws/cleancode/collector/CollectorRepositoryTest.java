package nl._42.qualityws.cleancode.collector;

import static org.junit.Assert.assertEquals;

import nl._42.qualityws.cleancode.shared.AbstractIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectorRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private CollectorRepository ownerRepository;

    @Test
    public void create() {
        final String expectedName = "Cornelis de Verzamelaar";
        Collector owner = new Collector();
        owner.setName(expectedName);
        ownerRepository.save(owner);
        owner = ownerRepository.findOne(owner.getId());
        assertEquals(expectedName, owner.getName());
    }

}