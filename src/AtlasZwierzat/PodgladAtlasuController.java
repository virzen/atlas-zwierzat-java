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
    private void zapiszNowyTyp() {
        String nazwa = this.poleNazwa.getText();
        String szacowanaLiczba = this.poleSzacowanaLiczba.getText();
        String typowaBudowaCiala = this.poleTypowaBudowaCiala.getText();
        
        EventBus.event(new Typ(nazwa, Integer.parseInt(szacowanaLiczba), typowaBudowaCiala));
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
    }       

}
