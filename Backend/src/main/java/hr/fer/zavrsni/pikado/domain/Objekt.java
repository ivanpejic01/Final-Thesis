package hr.fer.zavrsni.pikado.domain;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;
import org.hibernate.annotations.CollectionIdMutability;

import java.util.List;

@Entity
@Table(name = "objekt")
public class Objekt {

    @Id
    @GeneratedValue
    @Column(name = "id_objekt")
    private Long id;

    @Column(name = "naziv_objekt")
    private String nazivObjekt;

    @Column(name = "kapacitet")
    private Integer kapacitet;

    @ManyToOne
    @JoinColumn(name = "id_vrsta_objekt")
    private VrstaObjekta vrstaObjekta;

    @OneToMany(mappedBy = "objekt", cascade = CascadeType.ALL)
    private List<Turnir> turniri;




    public Objekt() {
    }

    public Objekt(Long id, String nazivObjekt, Integer kapacitet) {
        this.id = id;
        this.nazivObjekt = nazivObjekt;
        this.kapacitet = kapacitet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivObjekt() {
        return nazivObjekt;
    }

    public void setNazivObjekt(String nazivObjekt) {
        this.nazivObjekt = nazivObjekt;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }
}
