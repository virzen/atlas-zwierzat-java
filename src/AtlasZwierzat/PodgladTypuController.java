/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author virzen
 */
public class PodgladTypuController implements Initializable {
    @FXML
    private Text title;
    @FXML
    private TextField nazwaTypu;
    @FXML
    private TextField szacowanaLiczbaTypu;
    @FXML
    private TextField typowaBudowaCialaTypu;
    @FXML
    private TextField nazwaRodziny;
    @FXML
    private TextField cechaCharakterystycznaRodziny;
    @FXML
    private TextField sredniaLiczbaKonczynRodziny;
    @FXML
    private Button przyciskUsunTyp;
    @FXML
    private Button przyciskEdytujTyp;
    @FXML
    private Button przyciskUtworzRodzine;
    
    private Typ typ;

    public void setTyp(Typ typ) {
        this.typ = typ;
        this.title.setText("Typ: " + typ.getNazwa());
        this.nazwaTypu.setText(typ.getNazwa());
        this.szacowanaLiczbaTypu.setText(Integer.toString(typ.getSzacowanaLiczba()));
        this.typowaBudowaCialaTypu.setText(typ.getTypowaBudowaCiala());
    }
    
    @FXML
    private void edytujTyp() {
        String nazwa = this.nazwaTypu.getText();
        int szacowanaLiczba = Integer.parseInt(this.szacowanaLiczbaTypu.getText());
        String typowaBudowaCiala = this.typowaBudowaCialaTypu.getText();
        EdytowanyTyp edytowanyTyp = new EdytowanyTyp(nazwa, szacowanaLiczba, typowaBudowaCiala, this.typ.getId());
        
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.EDYTUJ, edytowanyTyp);
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void usunTyp() {
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.USUN, this.typ);
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void utworzRodzine() {
        String nazwa = this.nazwaRodziny.getText();
        String cechaCharakterystyczna = this.cechaCharakterystycznaRodziny.getText();
        int sredniaLiczbaKonczynRodziny = Integer.parseInt(this.sredniaLiczbaKonczynRodziny.getText());
        
        Rodzina nowaRodzina = new Rodzina(nazwa, cechaCharakterystyczna, sredniaLiczbaKonczynRodziny, this.typ);
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.UTWORZ, nowaRodzina);
        
        EventBus.event(zdarzenie);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
