public class Flyplass {
    private String sted;
    private Fly nesteAvgang;
    private Fly sisteAvgang;

    public Flyplass(String s) {
        sted = s;
        nesteAvgang = null;
        sisteAvgang = null;
    }

    public void flyAnkomst(Fly nyttFly) {
        // Midlertidig peker for flyene i køen
        Fly flyPeker = nesteAvgang;

        // Hvis det ikke er noen avganger fra før av
        if (nesteAvgang == null) {
            nesteAvgang = nyttFly;
            sisteAvgang = nyttFly;
            return;
        }

        // Sjekker om nyttFly har tidligere avgang enn den nåværende tidligste avgangen
        if (skalForan(nyttFly, nesteAvgang)) {
            plasserFremst(nyttFly);
            return; 
        }

        flyPeker = nesteAvgang.hentEtter();

        for (int i = 0; i < antallFly(); i++) {
            if (skalForan(nyttFly,flyPeker)){
                plasserFly(nyttFly, flyPeker);
            }
        }

        plasserBakerst(nyttFly);
    }

    public Fly flyAvgang(){
        if (nesteAvgang != sisteAvgang ) {
            Fly flygende = nesteAvgang;
            nesteAvgang = nesteAvgang.hentEtter();
            
            return flygende;
        } else {
            Fly flygende = nesteAvgang;

            nesteAvgang = null;
            sisteAvgang = null;

            return flygende;
        }
    }

    public String hentSted() {
        return sted;
    }

    public int antallFly() {
        Fly flyPeker = nesteAvgang;
        int teller = 0;
        while (flyPeker != null) {
            teller ++;
            flyPeker = flyPeker.hentEtter();
        }
        return teller;
    }

    @Override
    public String toString() {
        if (nesteAvgang == null) {
            return "Ingen kommende avganger";
        }
        String s = "";
        Fly flyPeker = nesteAvgang;
        for (int i = 0; i < antallFly(); i++) {
            s += flyPeker.toString() + "\n";
        }
        return s;
    }

    public Fly[] motsattRekkefoelge() {
        Fly arr[] = new Fly[antallFly()]; 
        Fly flyPeker = sisteAvgang;

        for (int i = antallFly() - 1; i <= 0; i--) {
            arr[i] = flyPeker;
            flyPeker = flyPeker.hentForan();
        }

        return arr;
    }

    private void plasserFremst(Fly nyttFly) {
        nyttFly.settEtter(nesteAvgang);
        nesteAvgang = nyttFly;
    }

    private void plasserBakerst(Fly nyttFly) {
        sisteAvgang.settEtter(nyttFly);
        sisteAvgang = nyttFly;
    }

    private boolean skalForan(Fly nyttFly, Fly nesteFly) {
        return nyttFly.compareTo(nesteFly) < 0;
    }

    private void plasserFly (Fly nyttFly, Fly nesteFly) {
        nesteFly.settForan(nyttFly);
        Fly forrigeFly = nesteFly.hentForan();
        forrigeFly.settEtter(nyttFly);
    }
}

