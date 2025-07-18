public class Main {
    public static void main(String[] args) {
        Risiko akzeptablesRisiko = new AkzeptablesRisiko("Lizenskosten der IDE steigt", 0.1f, 40000f);
        Risiko inakzeptablesRisiko = new InakzeptablesRisiko("DB Experte verlässt das Projekt", 1, 16000,
                "Erstaz bei Dienstleister reservieren");
        Risiko extremesRisiko = new ExtremesRisiko("Hauptauftraggeber meldet Insolvenz an", 1, 10000,
                "Versicherung abschließen", 50000);
        akzeptablesRisiko.druckeDaten();
        inakzeptablesRisiko.druckeDaten();
        extremesRisiko.druckeDaten();
    }

}
