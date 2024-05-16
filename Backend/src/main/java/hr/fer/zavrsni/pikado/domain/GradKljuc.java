package hr.fer.zavrsni.pikado.domain;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class GradKljuc implements Serializable {

    private Long idGrad;
    private Long idDrzava;

    public GradKljuc(Long idGrad, Long idDrzava) {
        this.idGrad = idGrad;
        this.idDrzava = idDrzava;
    }

    public Long getIdGrad() {
        return idGrad;
    }

    public void setIdGrad(Long idGrad) {
        this.idGrad = idGrad;
    }

    public Long getIdDrzava() {
        return idDrzava;
    }

    public void setIdDrzava(Long idDrzava) {
        this.idDrzava = idDrzava;
    }
}
