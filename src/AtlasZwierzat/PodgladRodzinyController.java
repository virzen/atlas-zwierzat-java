/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author virzen
 */
public class PodgladRodzinyController implements Initializable {
    @FXML private Text tytul;
    @FXML private TextField nazwaRodziny;
    @FXML private TextField cechaCharakterystycznaRodziny;
    @FXML private TextField sredniaLiczbaKonczynRodziny;
    @FXML private TextField nazwaGatunku;
    @FXML private DatePicker dataOdkryciaGatunku;
    @FXML private TextField liczbaKonczynGatunku;
    @FXML private TextField imieSlawnegoPrzedstawicielaGatunku;
    @FXML private Button przyciskUsunRodzine;
    @FXML private Button przyciskEdytujRodzine;
    @FXML private Button przyciskUtworzGatunek;
    @FXML private ListView<Gatunek> listaGatunkowKrzyzowki;
    @FXML private CheckBox jestKrzyzowkaCheckbox;
    
    private Rodzina rodzina;
    private EkranGlownyController rodzic;

    public void uzupelnijPola() {
        tytul.setText("Rodzina: " + rodzina.getNazwa());
        nazwaRodziny.setText(rodzina.getNazwa());
        cechaCharakterystycznaRodziny.setText(rodzina.getCechaCharakterystyczna());
        sredniaLiczbaKonczynRodziny.setText(Float.toString(rodzina.getSredniaLiczbaKonczyn()));
    }

    public void uzupelnijGatunki(List<Gatunek> gatunkiRodziny) {
        assert listaGatunkowKrzyzowki != null : "listaGatunkowKrzyzowki is null";

        listaGatunkowKrzyzowki.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<Gatunek> gatunki = FXCollections.observableArrayList(gatunkiRodziny);
        listaGatunkowKrzyzowki.setItems(gatunki);

        if (gatunkiRodziny.size() < 2) {
            jestKrzyzowkaCheckbox.setDisable(true);
            listaGatunkowKrzyzowki.setDisable(true);
            return;
        }
    }

    @FXML
    private void usunRodzine() {
        rodzic.usun(rodzina);
    }
    
    @FXML
    private void edytujRodzine() {
        String nazwa = this.nazwaRodziny.getText();
        String cechaCharakterystyczna = this.cechaCharakterystycznaRodziny.getText();
        float sredniaLiczbaKonczyn = Float.parseFloat(this.sredniaLiczbaKonczynRodziny.getText());

        rodzic.edytujRodzine(rodzina.getId(), nazwa, cechaCharakterystyczna, sredniaLiczbaKonczyn);
    }
    
    @FXML
    private void utworzGatunek() {
        assert listaGatunkowKrzyzowki.getSelectionModel().getSelectedItems().size() <= 2 : "too many selected species";

        String nazwa = nazwaGatunku.getText();
        LocalDate dataOdkrycia = dataOdkryciaGatunku.getValue();
        int liczbaKonczyn = Integer.parseInt(liczbaKonczynGatunku.getText());
        String imieSlawengoPrzedstawiciela = imieSlawnegoPrzedstawicielaGatunku.getText();

        Boolean jestKrzyzowka = jestKrzyzowkaCheckbox.isSelected();
        List<Gatunek> gatunki = new ArrayList<>(listaGatunkowKrzyzowki.getSelectionModel().getSelectedItems());

        if (jestKrzyzowka) {
            Krzyzowka nowaKrzyzowka = new Krzyzowka(nazwa, dataOdkrycia, liczbaKonczyn, imieSlawengoPrzedstawiciela, rodzina, gatunki);
            rodzic.dodaj(nowaKrzyzowka);

            return;
        }

        Gatunek nowyGatunek = new Gatunek(nazwa, dataOdkrycia, liczbaKonczyn, imieSlawengoPrzedstawiciela, rodzina);
        rodzic.dodaj(nowyGatunek);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: limit selected species to two
    }

    public void init(Rodzina rodzina, List<Gatunek> gatunkiRodziny, EkranGlownyController rodzic) {
        this.rodzina = rodzina;
        this.rodzic = rodzic;

        uzupelnijGatunki(gatunkiRodziny);
        uzupelnijPola();
    }
}
