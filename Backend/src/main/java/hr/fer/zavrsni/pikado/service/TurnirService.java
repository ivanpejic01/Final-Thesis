package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Objekt;
import hr.fer.zavrsni.pikado.domain.Osoba;
import hr.fer.zavrsni.pikado.domain.Turnir;
import hr.fer.zavrsni.pikado.dto.NadimakDTO;
import hr.fer.zavrsni.pikado.dto.TurnirRequestDTO;
import hr.fer.zavrsni.pikado.dto.TurnirUrediDTO;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TurnirService {

    List<Turnir> listAll();

    Optional<Turnir> findById(Long id);

    List<Turnir> odrzaniTurniri();

    Igrac dohvatiPobjednika(Long id);

    List<Igrac> igraciTurnir(Long id);

    Turnir ubaciTurnir(TurnirRequestDTO turnirRequestDTO);

    List<Turnir> sviTurniri();

    Turnir urediTurnir(Long id, TurnirUrediDTO turnirUrediDTO);

    Turnir pobjednikNaTurniru(Long id, NadimakDTO nadimakPobjednika);

    Map<Igrac, Integer> brojPobjeda();

    Map<Turnir, Map<String, Integer>> zeneMuskarci();

    Map<String, Integer> turniriMjeseci();
}
