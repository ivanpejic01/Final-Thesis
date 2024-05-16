package hr.fer.zavrsni.pikado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.fer.zavrsni.pikado.domain.Drzava;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

public class OsobaRequestDTO {

    private String ime;
    private String prezime;
    private String nadimak;
    private String oib;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datumRodenja;
    private String spol;
    private String drzava;
    private String email;
    private String brojMobitela;

    public OsobaRequestDTO(String ime, String prezime, String nadimak, String oib, LocalDate datumRodenja, String spol, String drzava, String email, String brojMobitela) {
        this.ime = ime;
        this.prezime = prezime;
        this.nadimak = nadimak;
        this.oib = oib;
        this.datumRodenja = datumRodenja;
        this.spol = spol;
        this.drzava = drzava;
        this.email = email;
        this.brojMobitela = brojMobitela;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getNadimak() {
        return nadimak;
    }

    public void setNadimak(String nadimak) {
        this.nadimak = nadimak;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojMobitela() {
        return brojMobitela;
    }

    public void setBrojMobitela(String brojMobitela) {
        this.brojMobitela = brojMobitela;
    }
}
