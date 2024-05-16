package hr.fer.zavrsni.pikado.service;

import hr.fer.zavrsni.pikado.domain.VrstaObjekta;
import hr.fer.zavrsni.pikado.domain.VrstaTurnira;

import java.util.List;
import java.util.Optional;

public interface VrstaObjektaService {

    List<VrstaObjekta> listAll();

    Optional<VrstaObjekta> findById(Long id);
}
