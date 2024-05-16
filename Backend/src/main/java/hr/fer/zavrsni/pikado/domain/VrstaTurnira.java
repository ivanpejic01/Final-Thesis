package hr.fer.zavrsni.pikado.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vrstaTurnira")
public class VrstaTurnira {
    @Id
    @GeneratedValue
    @Column(name = "idVrstaTurnira")
    private Long id;

    @Column(name = "nazivVrstaTurnira")
    private String nazivVrsta;

    @OneToMany(mappedBy = "vrstaTurnira" , cascade = CascadeType.ALL)
    private List<Turnir> turniri;

    public VrstaTurnira() {
    }

    public VrstaTurnira(Long id, String nazivVrsta) {
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
        return "Vrsta turnira" + this.nazivVrsta;
    }
}
