/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author virzen
 */
public class PodgladRodzinyController implements Initializable {
    @FXML
    private Text tytul;
    @FXML
    private TextField nazwaRodziny;
    @FXML
    private TextField cechaCharakterystycznaRodziny;
    @FXML 
    private TextField sredniaLiczbaKonczynRodziny;
    @FXML
    private TextField nazwaGatunku;
    @FXML
    private DatePicker dataOdkryciaGatunku;
    @FXML
    private TextField liczbaKonczynGatunku;
    @FXML
    private TextField imieSlawnegoPrzedstawicielaGatunku;
    @FXML
    private Button przyciskUsunRodzine;
    @FXML
    private Button przyciskEdytujRodzine;
    @FXML
    private Button przyciskUtworzGatunek;
    
    private Rodzina rodzina;
    
    public void setRodzina(Rodzina rodzina) {
        this.rodzina = rodzina;
        this.tytul.setText("Rodzina: " + rodzina.getNazwa());
        this.nazwaRodziny.setText(rodzina.getNazwa());
        this.cechaCharakterystycznaRodziny.setText(rodzina.getCechaCharakterystyczna());
        this.sredniaLiczbaKonczynRodziny.setText(Float.toString(rodzina.getSredniaLiczbaKonczyn()));
    }
    
    @FXML
    private void usunRodzine() {
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.USUN, this.rodzina);
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void edytujRodzine() {
        String nazwa = this.nazwaRodziny.getText();
        String cechaCharakterystyczna = this.cechaCharakterystycznaRodziny.getText();
        float sredniaLiczbaKonczyn = Float.parseFloat(this.sredniaLiczbaKonczynRodziny.getText());
        EdytowanaRodzina edytowanaRodzina = new EdytowanaRodzina(
            nazwa, 
            cechaCharakterystyczna, 
            sredniaLiczbaKonczyn, 
            this.rodzina.getTyp(), 
            this.rodzina.getId()
        );
        
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.EDYTUJ, edytowanaRodzina);
        EventBus.event(zdarzenie);
    }
    
    @FXML
    private void utworzGatunek() {
        String nazwa = this.nazwaGatunku.getText();
        LocalDate dataOdkrycia = this.dataOdkryciaGatunku.getValue();
        System.out.println(this.liczbaKonczynGatunku);
        int liczbaKonczyn = Integer.parseInt(this.liczbaKonczynGatunku.getText());
        String imieSlawengoPrzedstawiciela = this.imieSlawnegoPrzedstawicielaGatunku.getText();
        
        Gatunek nowyGatunek = new Gatunek(nazwa, dataOdkrycia, liczbaKonczyn, imieSlawengoPrzedstawiciela, this.rodzina);
        Zdarzenie zdarzenie = new Zdarzenie(TypZdarzenia.UTWORZ, nowyGatunek);
        
        EventBus.event(zdarzenie);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
