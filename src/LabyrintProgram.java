import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LabyrintProgram extends Application {
    private Labyrint labyrint;
    private Rute[][] brett;
    private int rader, kolonner;
    private FileChooser fileChooser = new FileChooser();
    private GridPane gridPane = new GridPane();
    private static final int MINTILESIZE = 1;

    class LabyrintKnapp extends Button{
        int x, y;

        LabyrintKnapp(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    class Klikkbehandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            LabyrintKnapp knapp = (LabyrintKnapp) event.getSource();
            Liste<String> utveier = labyrint.finnUtveiFra(knapp.y, knapp.x);
            ObservableList<Node> labyrintKnapper = gridPane.getChildren();

            if(utveier != null) {
                boolean[][] losning = losningStringTilTabell(utveier.hent(0), kolonner, rader);

                for (int i = 0; i < rader; i++) {
                    for (int j = 0; j < kolonner; j++) {
                        LabyrintKnapp k = (LabyrintKnapp) labyrintKnapper.get(i*kolonner+j);
                        if (losning[i][j]) {
                            k.setStyle("-fx-background-color: #00FF00;");
                        } else {
                            if(brett[i][j].charTilTegn().equals("#")) {
                                k.setStyle("-fx-background-color: #000000;");
                            }else{
                                k.setStyle("");
                            }
                        }
                    }
                }
            }else{
                for (int i = 0; i < rader; i++) {
                    for (int j = 0; j < kolonner; j++) {
                        LabyrintKnapp k = (LabyrintKnapp) labyrintKnapper.get(i*kolonner+j);
                        if(brett[i][j].charTilTegn().equals("#")) {
                            k.setStyle("-fx-background-color: #000000;");
                        }else{
                            k.setStyle("");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            labyrint = Labyrint.lesFraFil(fileChooser.showOpenDialog(primaryStage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        rader = labyrint.getRader();
        kolonner = labyrint.getKolonner();

        brett = labyrint.getBrett();

        for(int i = 0; i < rader; i++){
            for(int j = 0; j < kolonner; j++){
                Rute gjeldeneRute = brett[i][j];
                LabyrintKnapp knapp = new LabyrintKnapp(i,j);
                knapp.setMinSize(1, 1);
                knapp.setPrefSize(40, 40);
                if(gjeldeneRute.charTilTegn().equals("#")){
                    knapp.setStyle("-fx-background-color: #000000;");
                }else{
                    knapp.setOnAction(new Klikkbehandler());
                }
                gridPane.add(knapp, j, i);
            }
        }

        primaryStage.setTitle("Labyrint");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    /**
     * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
     * av losningstien.
     * @param losningString String-representasjon av utveien
     * @param bredde        bredde til labyrinten
     * @param hoyde         hoyde til labyrinten
     * @return              2D-representasjon av rutene der true indikerer at
     *                      ruten er en del av utveien.
     */
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }
}
