import java.util.ArrayList;

public abstract class Rute {
    private int radPosisjon;
    private int kolonnePosisjon;

    protected Rute nord, ost, sor, vest;
    protected Rute[] naboer;

    protected ArrayList<ArrayList<Rute>> utveier;
    protected ArrayList<Rute> kortesteUtvei;

    public Rute(int rad, int kolonne){
        radPosisjon = rad;
        kolonnePosisjon = kolonne;
    }

    public void setNaboer(Rute nord, Rute ost, Rute sor, Rute vest){
        this.nord = nord;
        this.ost = ost;
        this.sor = sor;
        this.vest = vest;
        naboer = new Rute[]{nord, ost, sor, vest};

        utveier = null;
        kortesteUtvei = null;
    }

    public void setKooridnater(int radPosisjon, int kolonnePosisjon){
        this.radPosisjon = radPosisjon;
        this.kolonnePosisjon = kolonnePosisjon;
    }

    public int getRadPosisjon() {
        return radPosisjon;
    }

    public int getKolonnePosisjon() {
        return kolonnePosisjon;
    }

    public Rute getNord() {
        return nord;
    }

    public Rute getOst() {
        return ost;
    }

    public Rute getSor() {
        return sor;
    }

    public Rute getVest() {
        return vest;
    }

    public abstract String charTilTegn();

    @Override
    public String toString(){
        return "(" + kolonnePosisjon + ", " + radPosisjon + ")";
    }
}
