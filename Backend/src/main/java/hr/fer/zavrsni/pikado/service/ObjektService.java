package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.Objekt;
import hr.fer.zavrsni.pikado.domain.Osoba;

import java.util.List;
import java.util.Optional;

public interface ObjektService {

    List<Objekt> listAll();

    Optional<Objekt> findById(Long id);

}
