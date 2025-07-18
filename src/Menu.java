import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {
    private static final float LIMIT = 10000.0f;
    private static final float KOSTENLIMIT = 1000000.0f;
    Risikoverwaltung risikoverwaltung;

    public Menu(Risikoverwaltung risikoverwaltung) {
        this.risikoverwaltung = risikoverwaltung;
    }

    public void druckeMenu() {
        System.out.println("Risikoverwaltung\n");
        System.out.println("1. Risiko aufnehmen");
        System.out.println("2. Zeige alle Risiken");
        System.out.println("3. Zeige Risiko mit maximaler Rückstellung");
        System.out.println("4. Berechne Summe aller Rückstellungen");
        System.out.println("5. Beenden\n");
        System.out.print("Bitte Menüpunkt wählen: ");
    }

    public void start() {
        boolean beendet = false;
        Scanner sc = new Scanner(System.in);
        while (!beendet) {
            druckeMenu();
            int eingabe = sc.nextInt();
            System.out.println();
            switch (eingabe) {
                case 1:
                    nehmeNeuesRisikoAuf();
                    break;
                case 2:
                    risikoverwaltung.zeigeRisiken();
                    break;
                case 3:
                    risikoverwaltung.sucheRisikoMitMaxRueckstellung();
                    break;
                case 4:
                    System.out.printf("Summe aller Rückstellungen: %.2f%n",
                            risikoverwaltung.berechneSummeRueckstellung());
                    break;
                case 5:
                    sc.close();
                    beendet = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void nehmeNeuesRisikoAuf() {
        String bezeichnung = JOptionPane.showInputDialog("Bezeichnung");
        if (bezeichnung == null)
            return;

        String eintrittswahrscheinlichkeitStr = JOptionPane.showInputDialog("Eintrittswahrscheinlichkeit");
        if (eintrittswahrscheinlichkeitStr == null)
            return;

        String kosten_im_schadensfallStr = JOptionPane.showInputDialog("Kosten im Schadensfall");
        if (kosten_im_schadensfallStr == null)
            return;

        float eintrittswahrscheinlichkeit = Float.parseFloat(eintrittswahrscheinlichkeitStr);
        float kosten_im_schadensfall = Float.parseFloat(kosten_im_schadensfallStr);
        float risikowert = Risiko.berechneRisikowert(eintrittswahrscheinlichkeit, kosten_im_schadensfall);

        if (risikowert < LIMIT)
            risikoverwaltung
                    .aufnehmen(new AkzeptablesRisiko(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall));
        else {
            String massnahmen = JOptionPane.showInputDialog("Maßnahmen");
            if (massnahmen == null)
                return;
            if (kosten_im_schadensfall <= KOSTENLIMIT) {
                risikoverwaltung.aufnehmen(new InakzeptablesRisiko(bezeichnung, eintrittswahrscheinlichkeit,
                        kosten_im_schadensfall, massnahmen));
            } else {
                String versicherungsbeitragStr = JOptionPane.showInputDialog("Versicherungsbeitrag");
                if (versicherungsbeitragStr == null)
                    return;
                float Versicherungsbeitrag = Float.parseFloat(versicherungsbeitragStr);
                risikoverwaltung.aufnehmen(new ExtremesRisiko(bezeichnung, eintrittswahrscheinlichkeit,
                        kosten_im_schadensfall, massnahmen, Versicherungsbeitrag));
            }
        }
    }
}
