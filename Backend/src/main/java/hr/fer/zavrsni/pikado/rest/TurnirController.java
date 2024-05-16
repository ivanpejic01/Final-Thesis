package hr.fer.zavrsni.pikado.rest;

import hr.fer.zavrsni.pikado.dao.TurnirRepository;
import hr.fer.zavrsni.pikado.domain.*;
import hr.fer.zavrsni.pikado.dto.NadimakDTO;
import hr.fer.zavrsni.pikado.dto.OsobaRequestDTO;
import hr.fer.zavrsni.pikado.dto.TurnirRequestDTO;
import hr.fer.zavrsni.pikado.dto.TurnirUrediDTO;
import hr.fer.zavrsni.pikado.service.TurnirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class TurnirController {

    @Autowired
    TurnirService turnirService;
    @Autowired
    TurnirRepository turnirRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/turniri")
    public List<Turnir> listaTurniri(){
        return turnirService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/turniri/{id}")
    public Optional<Turnir> dohvatiTurnir(@PathVariable("id") Long id) {
        return turnirService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/odrzani")
    public List<Turnir> odrzaniTurniri() {
        return turnirService.odrzaniTurniri();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/odrzani/{id}")
    public Igrac odrzaniTurniri(@PathVariable("id") Long id) {
        return turnirService.dohvatiPobjednika(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/igraci/turnir/{id}")
    public List<Igrac> listaIgracaTurnir (@PathVariable("id") Long id){
        return turnirService.igraciTurnir(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/sviTurniri")
    public List<Turnir> sviTurniri() {
        return turnirService.sviTurniri();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/novi")
    public List<Turnir> kreirajNoviTurnir(@RequestBody TurnirRequestDTO turnirRequestDTO) {
        Turnir turnir = turnirService.ubaciTurnir(turnirRequestDTO);
        return turnirRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/obrisi/{id}")
    public List<Turnir> obrisiTurnir(@PathVariable("id") Long id) {
        turnirRepository.delete(turnirRepository.findById(id).orElse(null));
        return turnirService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/uredi/{id}")
    public Turnir urediTurnir(@PathVariable("id") Long id, @RequestBody TurnirUrediDTO turnirUrediDTO) {

        return turnirService.urediTurnir(id, turnirUrediDTO);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/pobjednik/{id}")
    public Turnir dodajPobjednika(@PathVariable("id") Long id, @RequestBody NadimakDTO nadimakPobjednika) {

        return turnirService.pobjednikNaTurniru(id, nadimakPobjednika);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pobjedniciTablica")
    public Map<Igrac, Integer> brojPobjeda() {
        return turnirService.brojPobjeda();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/turnirOmjeri")
    public Map<Turnir, Map<String, Integer>> dohvatiZeneIMuskarce() {
        return turnirService.zeneMuskarci();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/turniriPoMjesecima")
    public Map<String, Integer> dohvatiTurnirePoMjesecima() {
        return turnirService.turniriMjeseci();
    }




}
