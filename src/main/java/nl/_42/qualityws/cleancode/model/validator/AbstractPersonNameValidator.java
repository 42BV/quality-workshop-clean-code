package nl._42.qualityws.cleancode.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl._42.qualityws.cleancode.model.CollectorsItem;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPersonNameValidator<T extends CollectorsItem>
        extends AbstractCollectorsItemValidator<T> {

    private static Pattern pattern = Pattern.compile("[a-zA-Z -\\.]+");

    public boolean verifyName(String name) {
        Matcher matcher = pattern.matcher(name);
        return  matcher.matches() &&
                Character.isUpperCase(name.charAt(0));
    }

}
