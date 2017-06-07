package nl._42.qualityws.cleancode.collector;

import org.hibernate.validator.constraints.NotEmpty;

public class CollectorForm {

    @NotEmpty
    public String name;
}
