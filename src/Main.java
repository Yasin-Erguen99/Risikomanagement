public class Main {
    public static void main(String[] args) {

        Risikoverwaltung risikoverwaltung = new Risikoverwaltung();
        // Risiko akzeptablesRisiko = new AkzeptablesRisiko("Lizenskosten der IDEsteigt", 1, 4000);
        // Risiko inakzeptablesRisiko = new InakzeptablesRisiko("DB Experte verlässt dasProjekt", 1, 100000,
        //         "Erstaz bei Dienstleister reservieren");
        // Risiko extremesRisiko = new ExtremesRisiko("Hauptauftraggeber meldetInsolvenz an", 1, 10000000,
        //         "Versicherung abschließen", 50000);
        // risikoverwaltung.aufnehmen(extremesRisiko);
        // risikoverwaltung.aufnehmen(akzeptablesRisiko);
        // risikoverwaltung.aufnehmen(inakzeptablesRisiko);
        Menu menu = new Menu(risikoverwaltung);
        menu.start();
    }

}
