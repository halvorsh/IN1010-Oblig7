import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Labyrint {
    private Rute[][] brett;
    private static int rader;
    private static int kolonner;

    private Labyrint(Rute[][] brett, int rader, int kolonner){
        this.brett = brett;
        this.rader = rader;
        this.kolonner = kolonner;
    }

    static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);

        String forsteLinje = scanner.nextLine();

        String[] forstelinjeBiter = forsteLinje.split(" ");

        rader = parseInt(forstelinjeBiter[0]);
        kolonner = parseInt(forstelinjeBiter[1]);

        Rute[][] brett = new Rute[rader][kolonner];

        int rad = 0;
        while(scanner.hasNextLine()){
            int kolonne = 0;
            String linje = scanner.nextLine();
            String[] biter = linje.split("");

            Rute[] raden = new Rute[kolonner];

            for(String bit : biter){
                Rute rute;

                if(bit.equals(".")){
                    if(rad == 0 || rad == rader-1 || kolonne == 0 || kolonne == kolonner -1){
                        rute = new Aapning(rad, kolonne);
                    }else {
                        rute = new HvitRute(rad, kolonne);
                    }
                }else if(bit.equals("#")){
                    rute = new SortRute(rad, kolonne);
                }else{
                    System.out.println("Feil ved filInnlesning");
                    break;
                }
                raden[kolonne] = rute;
                kolonne++;
            }
            brett[rad] = raden;
            rad++;
        }

        for(int i = 0; i < rader; i++){
            for(int j = 0; j < kolonner; j++){
                Rute gjeldeneRute = brett[i][j];

                Rute nord, ost, vest, sor;

                if(i==0){
                    nord = null;
                    sor = brett[i+1][j];
                }else if(i==rader-1){
                    sor = null;
                    nord = brett[i-1][j];
                }else{
                    nord = brett[i-1][j];
                    sor = brett[i+1][j];
                }

                if(j==0){
                    vest = null;
                    ost = brett[i][j+1];
                }else if(j==kolonner-1){
                    ost = null;
                    vest = brett[i][j-1];
                }else{
                    ost = brett[i][j+1];
                    vest = brett[i][j-1];
                }

                gjeldeneRute.setNaboer(nord, ost, sor, vest);
                gjeldeneRute.setKooridnater(i,j);
            }
        }

        for(Rute[] brettRad : brett){
            for(Rute rute : brettRad){
                if(rute instanceof HvitRute){
                    ((HvitRute) rute).settUtveier();
                }
            }
        }

        return new Labyrint(brett, rader, kolonner);
    }

    public Liste<String> finnUtveiFra(int startKol, int startRad){
        Rute startRute = brett[startRad][startKol];

        Liste<String> utvei = new Lenkeliste <>();

        System.out.println("Det er " + startRute.utveier.size() + " utvei(er) fra dette punktet.\nDen korteste er:");

        if(startRute.kortesteUtvei != null){
            for(Rute rute : startRute.kortesteUtvei){
                utvei.leggTil(rute.toString());
            }
        }

        return utvei;
    }

    @Override
    public String toString() {
        String output = "";

        for(Rute[] rad : brett){
            for(Rute rute : rad){
                output += rute.charTilTegn();
            }
            output += "\n";
        }

        return output;
    }
}
