package hr.fer.zavrsni.pikado.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "vrstaObjekta")
public class VrstaObjekta {

    @Id
    @GeneratedValue
    @Column(name = "id_vrsta_objekta")
    private Long id;

    @Column(name = "naziv_vrsta_objekta")
    private String nazivVrsta;

    @OneToMany(mappedBy = "vrstaObjekta", cascade = CascadeType.ALL)
    private List<Objekt> objekti;

    public VrstaObjekta() {
    }

    public VrstaObjekta(Long id, String nazivVrsta) {
        this.id = id;
        this.nazivVrsta = nazivVrsta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivVrsta() {
        return nazivVrsta;
    }

    public void setNazivVrsta(String nazivVrsta) {
        this.nazivVrsta = nazivVrsta;
    }

    @Override
    public String toString() {
        return "Vrsta turnira " + this.nazivVrsta;
    }
}
