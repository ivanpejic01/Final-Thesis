package hr.fer.zavrsni.pikado.rest;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class DemoController {

    @GetMapping("/prijava")
    @CrossOrigin(origins = "http://localhost:3000")
    public String sayHello() {
        return "Sad si u formi za prijavu";
    }
}
