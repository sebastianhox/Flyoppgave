public class Fly implements Comparable<Fly>{
    private String flynummer;
    private Flyplass fra;
    private Flyplass til;
    int avgangstid;
    Fly foran = null;
    Fly etter = null;

    public Fly(int atid, String flynr, Flyplass f, Flyplass t) {
        flynummer = flynr;
        fra = f;
        til = t;
        avgangstid = atid;
        
    }
    
    @Override
    public String toString() {
        return "Flight " + flynummer + "\nFrom " + fra + " to " + til + "\nDeparture: " + avgangstid;
    }

    @Override
    public int compareTo(Fly x) {
        if (avgangstid > x.hentAvgangstid()) {
            return 1;
        } else if (avgangstid < x.hentAvgangstid()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int hentAvgangstid() {
        return avgangstid;
    }

    // Neste
    public void settEtter(Fly x) {
        etter = x;
    }

    // Forrige
    public void settForan(Fly x){
        foran = x;
    }

    public Fly hentEtter() {
        return etter;
    }

    public Fly hentForan() {
        return foran;
    }

    public String hentTilSted() {
        return til.hentSted();
    }
}
