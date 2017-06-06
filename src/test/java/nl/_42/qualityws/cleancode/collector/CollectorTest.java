package nl._42.qualityws.cleancode.collector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectorTest {

    @Test
    public void construct() {
        final String expectedName = "Cornelis de Verzamelaar";
        Collector owner = new Collector();
        owner.setName(expectedName);
        assertEquals(expectedName, owner.getName());
    }

}