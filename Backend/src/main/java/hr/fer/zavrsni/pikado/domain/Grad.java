package hr.fer.zavrsni.pikado.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "grad")
public class Grad {

   @EmbeddedId
   private GradKljuc id;

    @Column(name = "pbr_grad")
    private Integer pbrGrad;

    @Column(name = "naziv_grad")
    private String nazivGrad;

    public Grad() {
    }

    public Grad(GradKljuc id, Integer pbrGrad, String nazivGrad) {
        this.id = id;
        this.pbrGrad = pbrGrad;
        this.nazivGrad = nazivGrad;
    }

    public GradKljuc getId() {
        return id;
    }

    public void setId(GradKljuc id) {
        this.id = id;
    }

    public Integer getPbrGrad() {
        return pbrGrad;
    }

    public void setPbrGrad(Integer pbrGrad) {
        this.pbrGrad = pbrGrad;
    }

    public String getNazivGrad() {
        return nazivGrad;
    }

    public void setNazivGrad(String nazivGrad) {
        this.nazivGrad = nazivGrad;
    }

    /*public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }*/

    @Override
    public String toString() {
        return "Grad " + this.nazivGrad + " pripada drzavi s postanskim brojem " +  this.pbrGrad;
    }
}
