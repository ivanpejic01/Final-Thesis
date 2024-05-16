package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.SudjelujeNa;
import hr.fer.zavrsni.pikado.domain.TurnirIgracKljuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SudjelujeNaRepository extends JpaRepository<SudjelujeNa, TurnirIgracKljuc> {

    boolean existsById(TurnirIgracKljuc id);

    List<SudjelujeNa> findAll();
}
