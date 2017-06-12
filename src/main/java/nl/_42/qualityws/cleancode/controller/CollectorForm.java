package nl._42.qualityws.cleancode.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class CollectorForm {

    @NotEmpty
    public String name;
}
