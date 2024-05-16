package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.Igrac;
import hr.fer.zavrsni.pikado.domain.Organizator;

import java.util.List;
import java.util.Optional;

public interface OrganizatorService {

    List<Organizator> listAll();
    Optional<Organizator> findById(Long id);
}
