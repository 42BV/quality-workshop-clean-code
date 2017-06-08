package nl._42.qualityws.cleancode.shared.test.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl._42.beanie.BeanBuilder;

@Component
public class CollectorBuilder {

    @Autowired
    private BeanBuilder beanBuilder;
    
    public CollectorBuildCommand collector(String name) {
        return beanBuilder.startAs(CollectorBuildCommand.class).withName(name);
    }
}
