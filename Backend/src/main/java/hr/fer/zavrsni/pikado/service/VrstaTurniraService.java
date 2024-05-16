package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.VrstaTurnira;

import java.util.List;
import java.util.Optional;

public interface VrstaTurniraService {

    List<VrstaTurnira> listAll();

    Optional<VrstaTurnira> findById(Long id);

}
