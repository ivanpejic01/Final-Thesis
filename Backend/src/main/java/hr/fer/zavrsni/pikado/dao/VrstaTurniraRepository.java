package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.VrstaTurnira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VrstaTurniraRepository extends JpaRepository<VrstaTurnira, Long> {

    boolean existsById(Long id);

    VrstaTurnira findByNazivVrsta(String nazivVrsta);
}
