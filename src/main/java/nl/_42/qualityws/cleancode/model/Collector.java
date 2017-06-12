package nl._42.qualityws.cleancode.model;

import javax.persistence.Entity;

import nl._42.qualityws.cleancode.shared.entity.AbstractEntity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Collector extends AbstractEntity {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}