import java.util.Objects;

public class ExtremesRisiko extends InakzeptablesRisiko {

    private float versicherungsbeitrag;

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
    public void druckeDaten() {
        System.out.printf("Id %d Extremes Risiko \"%s\" aus %d/%d;\nVersicherungsbeitrag %.2f; Massnahme \"%s\"\n",
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
