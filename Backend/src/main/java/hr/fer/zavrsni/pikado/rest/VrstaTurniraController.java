package hr.fer.zavrsni.pikado.rest;


import hr.fer.zavrsni.pikado.domain.VrstaTurnira;
import hr.fer.zavrsni.pikado.service.VrstaTurniraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class VrstaTurniraController {

    @Autowired
    VrstaTurniraService vrstaTurniraService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/vrste")
    public List<VrstaTurnira> vrsteTurnira() {
        return vrstaTurniraService.listAll();
    }
}
