import java.util.ArrayList;

public class HvitRute extends Rute{
    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    public void setNaboer(Rute nord, Rute ost, Rute sor, Rute vest) {
        super.setNaboer(nord, ost, sor, vest);
    }

    public void settUtveier(){
        utveier = finnUtveier("Ingen", new ArrayList <>());

        if(utveier != null){
            for(ArrayList<Rute> utvei : utveier){
                if(kortesteUtvei == null){
                    kortesteUtvei = utvei;
                }else if(utvei.size() < kortesteUtvei.size()){
                    kortesteUtvei = utvei;
                }
            }
        }
    }

    public ArrayList <ArrayList<Rute>> finnUtveier(String forrigeRetning, ArrayList<Rute> besokteRuter) {
        ArrayList<ArrayList<Rute>> funnedeUtveier = new ArrayList <>();
        if(besokteRuter.contains(this)){
            return null;
        }else{
            besokteRuter.add(this);
        }
        /*if(utveier != null){
            switch(forrigeRetning){
                case "Ingen": funnedeUtveier = utveier; System.out.println("Noe er galt, utveier skal ikke være definert."); break;
                case "Nord":
                    for(ArrayList<Rute> utvei : utveier){
                        if(utvei.get(0)!=nord){
                            funnedeUtveier.add(utvei);
                        }
                    }
                    break;
                case "Ost":
                    for(ArrayList<Rute> utvei : utveier){
                        if(utvei.get(0)!=ost){
                            funnedeUtveier.add(utvei);
                        }
                    }
                    break;
                case "Sor":
                    for(ArrayList<Rute> utvei : utveier){
                        if(utvei.get(0)!=sor){
                            funnedeUtveier.add(utvei);
                        }
                    }
                    break;
                case "Vest":
                    for(ArrayList<Rute> utvei : utveier){
                        if(utvei.get(0)!=vest){
                            funnedeUtveier.add(utvei);
                        }
                    }
                    break;
                default: System.out.println("Noe er veldig veldig galt, sjekk store bokstaver ved retning."); break;
            }
        }else{*/
            switch(forrigeRetning){
                case "Ingen":
                    if(nord instanceof HvitRute){
                        HvitRute hvitNord = (HvitRute) nord;
                        ArrayList<ArrayList<Rute>> nordUtveier = hvitNord.finnUtveier("Sor", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, nordUtveier);
                    }
                    if(ost instanceof HvitRute){
                        HvitRute hvitOst = (HvitRute) ost;
                        ArrayList<ArrayList<Rute>> ostUtveier = hvitOst.finnUtveier("Vest", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, ostUtveier);
                    }
                    if(sor instanceof HvitRute){
                        HvitRute hvitSor = (HvitRute) sor;
                        ArrayList<ArrayList<Rute>> sorUtveier = hvitSor.finnUtveier("Nord", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, sorUtveier);
                    }
                    if(vest instanceof HvitRute){
                        HvitRute hvitVest = (HvitRute) vest;
                        ArrayList<ArrayList<Rute>> vestUtveier = hvitVest.finnUtveier("Ost", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, vestUtveier);
                    }
                    break;
                case "Nord":
                    if(ost instanceof HvitRute){
                        HvitRute hvitOst = (HvitRute) ost;
                        ArrayList<ArrayList<Rute>> ostUtveier = hvitOst.finnUtveier("Vest", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, ostUtveier);
                    }
                    if(sor instanceof HvitRute){
                        HvitRute hvitSor = (HvitRute) sor;
                        ArrayList<ArrayList<Rute>> sorUtveier = hvitSor.finnUtveier("Nord", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, sorUtveier);
                    }
                    if(vest instanceof HvitRute){
                        HvitRute hvitVest = (HvitRute) vest;
                        ArrayList<ArrayList<Rute>> vestUtveier = hvitVest.finnUtveier("Ost", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, vestUtveier);
                    }
                    break;
                case "Ost":
                    if(nord instanceof HvitRute){
                        HvitRute hvitNord = (HvitRute) nord;
                        ArrayList<ArrayList<Rute>> nordUtveier = hvitNord.finnUtveier("Sor", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, nordUtveier);
                    }
                    if(sor instanceof HvitRute){
                        HvitRute hvitSor = (HvitRute) sor;
                        ArrayList<ArrayList<Rute>> sorUtveier = hvitSor.finnUtveier("Nord", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, sorUtveier);
                    }
                    if(vest instanceof HvitRute){
                        HvitRute hvitVest = (HvitRute) vest;
                        ArrayList<ArrayList<Rute>> vestUtveier = hvitVest.finnUtveier("Ost", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, vestUtveier);
                    }
                    break;
                case "Sor":
                    if(nord instanceof HvitRute){
                        HvitRute hvitNord = (HvitRute) nord;
                        ArrayList<ArrayList<Rute>> nordUtveier = hvitNord.finnUtveier("Sor", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, nordUtveier);
                    }
                    if(ost instanceof HvitRute){
                        HvitRute hvitOst = (HvitRute) ost;
                        ArrayList<ArrayList<Rute>> ostUtveier = hvitOst.finnUtveier("Vest", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, ostUtveier);
                    }
                    if(vest instanceof HvitRute){
                        HvitRute hvitVest = (HvitRute) vest;
                        ArrayList<ArrayList<Rute>> vestUtveier = hvitVest.finnUtveier("Ost", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, vestUtveier);
                    }
                    break;
                case "Vest":
                    if(nord instanceof HvitRute){
                        HvitRute hvitNord = (HvitRute) nord;
                        ArrayList<ArrayList<Rute>> nordUtveier = hvitNord.finnUtveier("Sor", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, nordUtveier);
                    }
                    if(ost instanceof HvitRute){
                        HvitRute hvitOst = (HvitRute) ost;
                        ArrayList<ArrayList<Rute>> ostUtveier = hvitOst.finnUtveier("Vest", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, ostUtveier);
                    }
                    if(sor instanceof HvitRute){
                        HvitRute hvitSor = (HvitRute) sor;
                        ArrayList<ArrayList<Rute>> sorUtveier = hvitSor.finnUtveier("Nord", besokteRuter);

                        sjekkOmUtveierErGyldige(funnedeUtveier, sorUtveier);
                    }
                    break;
                default: System.out.println("Error med pathfinding");
            }
        /*}*/

        if(this instanceof Aapning){
            funnedeUtveier.add(new ArrayList <>());
        }

        if(funnedeUtveier.size() == 0){
            funnedeUtveier = null;
        }else{
            for(ArrayList<Rute> utvei : funnedeUtveier){
                utvei.add(0, this);
            }
        }
        besokteRuter.remove(this);
        return funnedeUtveier;
    }

    private void sjekkOmUtveierErGyldige(ArrayList<ArrayList<Rute>> samletUtveiListe, ArrayList<ArrayList<Rute>> listeSomSkalSjekkes){
        if(listeSomSkalSjekkes != null){
            samletUtveiListe.addAll(listeSomSkalSjekkes);
        }
    }

    @Override
    public String charTilTegn() {
        return ".";
    }
}
