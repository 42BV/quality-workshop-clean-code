package nl._42.qualityws.cleancode.collectors_item;

import org.hibernate.validator.constraints.NotEmpty;

public class CollectorsItemForm {

    @NotEmpty
    public String name;
    public Long collector;
}
