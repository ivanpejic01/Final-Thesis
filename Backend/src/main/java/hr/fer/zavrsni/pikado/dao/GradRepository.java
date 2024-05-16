package hr.fer.zavrsni.pikado.dao;

import hr.fer.zavrsni.pikado.domain.Grad;
import hr.fer.zavrsni.pikado.domain.GradKljuc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradRepository extends JpaRepository<Grad, GradKljuc> {

}
