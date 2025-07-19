import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Risiko implements Comparable<Risiko> {
    private final int id;
    private static int anzahlRisiken = 0;
    private String bezeichnung;
    private float eintrittswahrscheinlichkeit;
    private float kosten_im_schadensfall;
    private LocalDate erstellungsdatum;

    public Risiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        this.id = anzahlRisiken++;
        this.bezeichnung = bezeichnung;
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
        this.kosten_im_schadensfall = kosten_im_schadensfall;
        this.erstellungsdatum = LocalDate.now();
    }

    public int getId() {
        return this.id;
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

    public static float berechneRisikowert(float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        return eintrittswahrscheinlichkeit * kosten_im_schadensfall;
    }

    public float berechneRisikowert() {
        return Risiko.berechneRisikowert(eintrittswahrscheinlichkeit, kosten_im_schadensfall);
    }

    public abstract float ermittleRueckstellung();

    public abstract void druckeDaten(OutputStream stream);

    @Override
    public boolean equals(Object o) {
        // 1. Identische Referenz?
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        // 3. Typ-Cast und Attributvergleich
        Risiko risiko = (Risiko) o;
        return Float.compare(eintrittswahrscheinlichkeit, risiko.eintrittswahrscheinlichkeit) == 0 &&
                Float.compare(kosten_im_schadensfall, risiko.kosten_im_schadensfall) == 0 &&
                Objects.equals(bezeichnung, risiko.bezeichnung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
    }

    @Override
    public int compareTo(Risiko o) {
        return Float.compare(berechneRisikowert(), o.berechneRisikowert());
    }

}
