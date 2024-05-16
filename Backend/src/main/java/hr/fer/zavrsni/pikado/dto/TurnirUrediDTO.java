package hr.fer.zavrsni.pikado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TurnirUrediDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datumOdrzavanja;

    private String vrijemeOdrzavanja;

    private String nazivObjekta;

    public TurnirUrediDTO() {
    }

    public TurnirUrediDTO(LocalDate datumOdrzavanja, String vrijemeOdrzavanja, String nazivObjekta) {
        this.datumOdrzavanja = datumOdrzavanja;
        this.vrijemeOdrzavanja = vrijemeOdrzavanja;
        this.nazivObjekta = nazivObjekta;
    }

    public LocalDate getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(LocalDate datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
    }

    public String getVrijemeOdrzavanja() {
        return vrijemeOdrzavanja;
    }

    public void setVrijemeOdrzavanja(String vrijemeOdrzavanja) {
        this.vrijemeOdrzavanja = vrijemeOdrzavanja;
    }

    public String getNazivObjekta() {
        return nazivObjekta;
    }

    public void setNazivObjekta(String nazivObjekta) {
        this.nazivObjekta = nazivObjekta;
    }
}
