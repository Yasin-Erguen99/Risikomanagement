import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

public class ExtremesRisiko extends InakzeptablesRisiko {

    private float versicherungsbeitrag;
    private static final long serialVersionUID = 1L;

    public ExtremesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall,
            String massnahme, float versicherungsbeitrag) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall, massnahme);
        this.versicherungsbeitrag = versicherungsbeitrag;

    }

    public float getVersicherungsbeitrag() {
        return versicherungsbeitrag;
    }

    public void setVersicherungsbeitrag(float versicherungsbeitrag) {
        this.versicherungsbeitrag = versicherungsbeitrag;
    }

    @Override
    public float ermittleRueckstellung() {
        return this.versicherungsbeitrag;
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        PrintWriter printWriter = new PrintWriter(stream, true);
        printWriter.printf("Id %d Extremes Risiko \"%s\" aus %d/%d;\nVersicherungsbeitrag %.2f; Massnahme \"%s\"\n",
                getId(), getBezeichnung(), getErstellungsdatum().getMonthValue(), getErstellungsdatum().getYear(),
                getVersicherungsbeitrag(), getMassnahme());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        ExtremesRisiko extremesRisiko = (ExtremesRisiko) o;
        return Float.compare(versicherungsbeitrag, extremesRisiko.versicherungsbeitrag) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versicherungsbeitrag);
    }

}
