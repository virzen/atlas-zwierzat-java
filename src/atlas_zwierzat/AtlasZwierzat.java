/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlas_zwierzat;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author virzen
 */
public class AtlasZwierzat extends Application {
    private List<Typ> typy = new ArrayList<>();
    private List<Rodzina> rodziny = new ArrayList<>();
    private List<Gatunek> gatunki = new ArrayList<>();
    
    public AtlasZwierzat() {
    }
    
    public List<Typ> getTypy() {
        return typy;
    }

    /**
     *
     * @param typy
     */
    public void setTypy(List<Typ> typy) {
        this.typy = typy;
    }

    public List<Rodzina> getRodziny() {
        return rodziny;
    }

    /**
     *
     * @param rodziny
     */
    public void setRodziny(List<Rodzina> rodziny) {
        this.rodziny = rodziny;
    }

    public List<Gatunek> getGatunki() {
        return gatunki;
    }

    /**
     *
     * @param gatunki
     */
    public void setGatunki(List<Gatunek> gatunki) {
        this.gatunki = gatunki;
    }
    
    /**
     *
     * @param typ
     */
    public void dodajTyp(Typ typ) {
        this.typy.add(typ);
    }
    
    /**
     *
     * @param typ
     */
    public void usunTyp(Typ typ) {
        int index = this.typy.indexOf(typ);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param rodzina
     */
    public void dodajRodzine(Rodzina rodzina) {
        this.rodziny.add(rodzina);
    }
    
    /**
     *
     * @param rodzina
     */
    public void usunRodzine(Rodzina rodzina) {
        int index = this.rodziny.indexOf(rodzina);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param gatunek
     */
    public void dodajGatunek(Gatunek gatunek) {
        this.gatunki.add(gatunek);
    }
    
    /**
     *
     * @param gatunek
     */
    public void usunGatunek(Gatunek gatunek) {
        int index = this.gatunki.indexOf(gatunek);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param zapytanie
     * @return
     */
    public List<Gatunek> filtrujGatunki(String zapytanie) {
        List<Gatunek> przefiltrowaneGatunki = new ArrayList<>();
        
        for (Gatunek gatunek : this.gatunki) {
            if (gatunek.getNazwa().contains(zapytanie)) {
                przefiltrowaneGatunki.add(gatunek);
            }
        }
        
        return przefiltrowaneGatunki;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
