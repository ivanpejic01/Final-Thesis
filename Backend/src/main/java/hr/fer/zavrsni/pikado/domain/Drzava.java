package hr.fer.zavrsni.pikado.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "drzava")
public class Drzava {

    @Id
    @GeneratedValue
    @Column(name = "id_drzava")
    private Long id;

    @Column(name = "naziv_drzava")
    private String naziv;

    public Drzava() {
    }

    public Drzava(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Drzava " + this.naziv;
    }
}
