import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Risikoverwaltung {
    List<Risiko> risiken;

    public Risikoverwaltung() {
        risiken = new ArrayList<Risiko>();
    }

    public void aufnehmen(Risiko risiko) {
        risiken.add(risiko);
    }

    public void zeigeRisiken() {
        for (Risiko r : risiken) {
            r.druckeDaten();
        }
    }

    public void sucheRisikoMitMaxRueckstellung() {
        Risiko risikoMitMaxRueckstellung = null;
        float maxRueckstellung = Float.MIN_VALUE;
        Iterator<Risiko> it = risiken.iterator();
        while (it.hasNext()) {
            Risiko next = it.next();
            if (next.ermittleRueckstellung() > maxRueckstellung) {
                maxRueckstellung = next.ermittleRueckstellung();
                risikoMitMaxRueckstellung = next;
            }
        }
        if (risikoMitMaxRueckstellung != null) {
            risikoMitMaxRueckstellung.druckeDaten();
        } else {
            System.out.println("Es sind keine Risiken in der Verwaltung vorhanden.");
        }

    }

    public float berechneSummeRueckstellung() {
        float summeRueckstellung = 0.0f;
        for (Risiko r : risiken) {
            summeRueckstellung += r.ermittleRueckstellung();
        }
        return summeRueckstellung;
    }
}
