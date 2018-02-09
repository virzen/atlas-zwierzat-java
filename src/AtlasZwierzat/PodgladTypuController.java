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
    private EkranGlownyController rodzic;

    public void uzuplenijPola() {
        title.setText("Typ: " + typ.getNazwa());
        nazwaTypu.setText(typ.getNazwa());
        szacowanaLiczbaTypu.setText(Integer.toString(typ.getSzacowanaLiczba()));
        typowaBudowaCialaTypu.setText(typ.getTypowaBudowaCiala());
    }
    
    @FXML
    private void edytujTyp() {
        String nazwa = nazwaTypu.getText();
        int szacowanaLiczba = Integer.parseInt(szacowanaLiczbaTypu.getText());
        String typowaBudowaCiala = typowaBudowaCialaTypu.getText();

        rodzic.edytujTyp(typ.getId(), nazwa, szacowanaLiczba, typowaBudowaCiala);
    }
    
    @FXML
    private void usunTyp() {
        rodzic.usun(typ);
    }
    
    @FXML
    private void utworzRodzine() {
        String nazwa = this.nazwaRodziny.getText();
        String cechaCharakterystyczna = this.cechaCharakterystycznaRodziny.getText();
        int sredniaLiczbaKonczynRodziny = Integer.parseInt(this.sredniaLiczbaKonczynRodziny.getText());
        
        Rodzina nowaRodzina = new Rodzina(nazwa, cechaCharakterystyczna, sredniaLiczbaKonczynRodziny, this.typ);
        rodzic.dodaj(nowaRodzina);
    }

    public void init(Typ typ, EkranGlownyController rodzic) {
        this.typ = typ;
        this.rodzic = rodzic;

        uzuplenijPola();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
