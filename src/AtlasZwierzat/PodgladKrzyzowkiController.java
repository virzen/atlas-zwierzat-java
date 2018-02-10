package AtlasZwierzat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PodgladKrzyzowkiController extends PodgladGatunkuController implements Initializable  {
    @FXML
    Text tytul;
    @FXML
    Button przyciskUsun;
    @FXML
    TextField poleNazwa;
    @FXML
    DatePicker poleDataOdkrycia;
    @FXML
    TextField poleLiczbaKonczyn;
    @FXML
    TextField poleImieSlawnegoPrzedstawiciela;
    @FXML
    ListView<Gatunek> poleGatunki;

    private Krzyzowka krzyzowka;
    private EkranGlownyController rodzic;


    @FXML
    private void usun() {
        this.rodzic.usun(this.krzyzowka);
    }

    /**
     * Ustawia nazwe krzyzowki jako tytul widoku
     */
    private void ustawTytul() {
        assert tytul != null : "tytul nie jest zainicjalizowany";

        tytul.setText("Krzyzowka: " + this.krzyzowka.getNazwa());
    }

    /**
     * Uzupelnia pola formularza danymi krzyzowki
     */
    private void uzupelnijPola() {
        poleNazwa.setText(krzyzowka.getNazwa());
        poleDataOdkrycia.setValue(krzyzowka.getDataOdkrycia());
        poleLiczbaKonczyn.setText(Integer.toString(krzyzowka.getLiczbaKonczyn()));
        poleImieSlawnegoPrzedstawiciela.setText(krzyzowka.getImieSlawnegoPrzedstawiciela());

        List<Gatunek> wszystkieGatunki = rodzic.getAtlas().znajdzGatunkiRodziny(krzyzowka.getRodzina());
        List<Gatunek> wybraneGatunki = krzyzowka.getGatunki();
        poleGatunki.setItems(FXCollections.observableArrayList(wszystkieGatunki));

        wybraneGatunki.stream().forEach(gatunek -> {
            int index = wszystkieGatunki.indexOf(gatunek);
            poleGatunki.getSelectionModel().select(index);
        });
    }

    @FXML
    private void edytuj() {
        String nazwa = poleNazwa.getText();
        LocalDate dataOdkrycia = poleDataOdkrycia.getValue();
        int liczbaKonczyn = Integer.parseInt(poleLiczbaKonczyn.getText());
        String imieSlawnegoPrzedstawieciela = poleImieSlawnegoPrzedstawiciela.getText();
        List<Gatunek> gatunki = new ArrayList<>(poleGatunki.getSelectionModel().getSelectedItems());
        int id = krzyzowka.getId();

        rodzic.edytujKrzyzowke(id, nazwa, dataOdkrycia, liczbaKonczyn, imieSlawnegoPrzedstawieciela, gatunki);
    }

    public void init(Krzyzowka krzyzowka, EkranGlownyController rodzic) {
        this.krzyzowka = krzyzowka;
        this.rodzic = rodzic;

        ustawTytul();
        uzupelnijPola();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poleGatunki.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
