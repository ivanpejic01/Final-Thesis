package hr.fer.zavrsni.pikado.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WelcomeController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/welcome")
    public String welcome() {
        return "Hello world!";
    }
}
