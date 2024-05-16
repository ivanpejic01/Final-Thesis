package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.VrstaObjekta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VrstaObjektaRepository extends JpaRepository<VrstaObjekta, Long> {

    boolean existsById(Long id);
}
