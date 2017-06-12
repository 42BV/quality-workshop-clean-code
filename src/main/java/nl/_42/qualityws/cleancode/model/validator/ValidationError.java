package nl._42.qualityws.cleancode.model.validator;

public class ValidationError {

    private final String description;

    public ValidationError(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
