import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

public class InakzeptablesRisiko extends Risiko {

    private String massnahme;
    private static final long serialVersionUID = 1L;

    public InakzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall,
            String massnahme) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
        this.massnahme = massnahme;
    }

    public String getMassnahme() {
        return massnahme;
    }

    public void setMassnahme(String massnahme) {
        this.massnahme = massnahme;
    }

    @Override
    public float ermittleRueckstellung() {
        return berechneRisikowert();
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        PrintWriter printWriter = new PrintWriter(stream, true);
        printWriter.printf(
                "Id %d Inakzeptables Risiko \"%s\" aus %d/%d;\nRisikowert %.2f; Rueckstellung %.2f;\nMassnahme \"%s\"\n",
                getId(), getBezeichnung(), getErstellungsdatum().getMonthValue(), getErstellungsdatum().getYear(),
                berechneRisikowert(), ermittleRueckstellung(), getMassnahme());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        InakzeptablesRisiko inakzeptablesRisiko = (InakzeptablesRisiko) o;
        return Objects.equals(massnahme, inakzeptablesRisiko.massnahme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), massnahme);
    }

}
