import java.time.LocalDate;

public abstract class Risiko {
    @SuppressWarnings("unused")
    private final int id;
    private String bezeichnung;
    private float eintrittswahrscheinlichkeit;
    private float kosten_im_schadensfall;
    private LocalDate erstellungsdatum;

    public Risiko(int id, String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
        this.kosten_im_schadensfall = kosten_im_schadensfall;
        this.erstellungsdatum = LocalDate.now();
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public float getEintrittswahrscheinlichkeit() {
        return eintrittswahrscheinlichkeit;
    }

    public void setEintrittswahrscheinlichkeit(float eintrittswahrscheinlichkeit) {
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
    }

    public float getKosten_im_schadensfall() {
        return kosten_im_schadensfall;
    }

    public void setKosten_im_schadensfall(float kosten_im_schadensfall) {
        this.kosten_im_schadensfall = kosten_im_schadensfall;
    }

    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public float berechneRisikowert() {
        return Risiko.berechneRisikowert(this.eintrittswahrscheinlichkeit, this.kosten_im_schadensfall);
    }

    public static float berechneRisikowert(float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        return eintrittswahrscheinlichkeit * kosten_im_schadensfall;
    }

    public abstract float ermittleRueckstellung();

    public abstract void druckeDaten();

}
