import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Risikoverwaltung {
    private List<Risiko> risiken;

    public Risikoverwaltung() {
        risiken = new ArrayList<Risiko>();
    }

    public void aufnehmen(Risiko risiko) {
        risiken.add(risiko);
    }

    public void zeigeRisiken(OutputStream stream) {
        Collections.sort(risiken);
        for (Risiko r : risiken) {
            r.druckeDaten(stream);
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
            risikoMitMaxRueckstellung.druckeDaten(System.out);
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

 
    public List<Risiko> getRisiken() {
        return risiken;
    }


    public void setRisiken(List<Risiko> risiken) {
        this.risiken = risiken;
    }

}
