/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

/**
 * FXML Controller class
 *
 * @author virzen
 */
public class PodgladAtlasuController implements Initializable {
    @FXML
    private TextField poleNazwa;
    @FXML
    private TextField poleSzacowanaLiczba;
    @FXML
    private TextField poleTypowaBudowaCiala;
    @FXML
    private Button przyciskZapiszAtlas;
    @FXML 
    private Button przyciskWczytajAtlas;
    @FXML
    private TextField poleNazwaPliku;
    
    private String getNazwaPliku() {
        return this.poleNazwaPliku.getText();
    }

    @FXML
    private void zapiszNowyTyp() {
        String nazwa = this.poleNazwa.getText();
        String szacowanaLiczba = this.poleSzacowanaLiczba.getText();
        String typowaBudowaCiala = this.poleTypowaBudowaCiala.getText();
        
        Typ nowyTyp = new Typ(nazwa, Integer.parseInt(szacowanaLiczba), typowaBudowaCiala);
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.UTWORZ, nowyTyp);
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void zapiszAtlas() {
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.ZAPISZ_ATLAS, this.getNazwaPliku());
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void wczytajAtlas() {
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.WCZYTAJ_ATLAS, this.getNazwaPliku());
        EventBus.event(zdarzenie);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.poleSzacowanaLiczba.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.poleSzacowanaLiczba.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        this.poleNazwaPliku.setText(SerializerAtlasu.getDomyslnaNazwaPliku());
    }       

}
