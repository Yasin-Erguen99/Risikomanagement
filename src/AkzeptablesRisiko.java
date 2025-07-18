public class AkzeptablesRisiko extends Risiko {

    public AkzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
    }

    @Override
    public float ermittleRueckstellung() {
        return 0f;
    }

    @Override
    public void druckeDaten() {
        System.out.printf("Id %d Akzeptables Risiko \"%s\" aus %d/%d;\nRisikowert %.2f; Rueckstellung %.2f\n", getId(),
                getBezeichnung(), getErstellungsdatum().getMonthValue(), getErstellungsdatum().getYear(),
                berechneRisikowert(), ermittleRueckstellung());
    }
   
}