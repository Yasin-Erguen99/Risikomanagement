import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.List;
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
        System.out.println("3. Risikoliste in Datei schreiben");
        System.out.println("4. Zeige Risiko mit maximaler Rückstellung");
        System.out.println("5. Berechne Summe aller Rückstellungen");
        System.out.println("6. Speichern");
        System.out.println("7. Laden");
        System.out.println("8. Beenden\n");
        System.out.print("Bitte Menüpunkt wählen: ");
    }

    public void start() {
        boolean beendet = false;
        Scanner sc = new Scanner(System.in);
        while (!beendet) {
            druckeMenu();

            Integer eingabe = leseMenuAuswahl(sc);
            if (eingabe != null)
                switch (eingabe) {
                    case 1:
                        nehmeNeuesRisikoAuf();
                        break;
                    case 2:
                        risikoverwaltung.zeigeRisiken(System.out);
                        break;
                    case 3:
                        inDateiSchreiben();
                        break;
                    case 4:
                        risikoverwaltung.sucheRisikoMitMaxRueckstellung();
                        break;
                    case 5:
                        System.out.printf("Summe aller Rückstellungen: %.2f%n",
                                risikoverwaltung.berechneSummeRueckstellung());
                        break;
                    case 6:
                        speichern();
                        break;
                    case 7:
                        laden();
                        break;
                    case 8:
                        sc.close();
                        beendet = true;
                        break;
                    default:
                        System.out.println("Ungültige Auswahl. Bitte wählen Sie 1-5.\n");
                        break;
                }

        }
    }

    public void speichern() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("risiken.ser")))) {
            out.writeObject(risikoverwaltung.getRisiken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void laden() {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("risiken.ser")))) {
            List<Risiko> risikoListe = (List<Risiko>) in.readObject();
            risikoverwaltung.setRisiken(risikoListe);
            Risiko.setAnzahlRisiken(risikoverwaltung.getRisiken().size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void inDateiSchreiben() {
        String dateiName = "";
        boolean dateiNameGültig = false;
        while (!dateiNameGültig) {
            dateiName = JOptionPane.showInputDialog("Dateinamen eingben");
            if (dateiName == null)
                return;
            if (dateiName.isBlank()) {
                int antwort = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! Neuen Dateinamen eingeben?",
                        "Hinweis", JOptionPane.YES_NO_OPTION);
                if (antwort == JOptionPane.NO_OPTION)
                    return;
            } else {
                dateiNameGültig = true;
                dateiName += ".liste";

            }
        }

        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(dateiName))) {
            risikoverwaltung.zeigeRisiken(out);
        } catch (IOException e) {
        }
    }

    private Integer leseMenuAuswahl(Scanner sc) {
        try {
            int eingabe = sc.nextInt();
            System.out.println();
            return eingabe;
        } catch (InputMismatchException e) {
            System.out.println("Bitte gültige Zahl eingeben!");
            sc.nextLine();
            return null;
        }
    }

    public void nehmeNeuesRisikoAuf() {
        String bezeichnung = JOptionPane.showInputDialog("Bezeichnung");
        if (bezeichnung == null)
            return;
        Float eintrittswahrscheinlichkeit = eingabeFloat("Eintrittswahrscheinlichkeit");
        if (eintrittswahrscheinlichkeit == null)
            return;
        Float kosten_im_schadensfall = eingabeFloat("Kosten im Schadensfall");
        if (kosten_im_schadensfall == null)
            return;
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
                Float versicherungsbeitrag = eingabeFloat("Versicherungsbeitrag");
                if (versicherungsbeitrag == null)
                    return;
                risikoverwaltung.aufnehmen(new ExtremesRisiko(bezeichnung, eintrittswahrscheinlichkeit,
                        kosten_im_schadensfall, massnahmen, versicherungsbeitrag));
            }
        }
    }

    private Float eingabeFloat(String prompt) {
        String eingabeStr = JOptionPane.showInputDialog(prompt);
        if (eingabeStr == null)
            return null;

        while (true) {
            try {
                return Float.parseFloat(eingabeStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte gültigen Wert eingeben");
                eingabeStr = JOptionPane.showInputDialog(prompt);
                if (eingabeStr == null)
                    return null;
            }
        }
    }
}
