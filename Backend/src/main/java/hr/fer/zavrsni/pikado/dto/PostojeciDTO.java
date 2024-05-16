package hr.fer.zavrsni.pikado.dto;

public class PostojeciDTO {

    private String nadimak;

    private String oib;

    public PostojeciDTO() {
    }

    public PostojeciDTO(String nadimak, String oib) {
        this.nadimak = nadimak;
        this.oib = oib;
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
}
