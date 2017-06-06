package nl._42.qualityws.cleancode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping
    public String sayHi() {
        return "Hello world!";
    }

}