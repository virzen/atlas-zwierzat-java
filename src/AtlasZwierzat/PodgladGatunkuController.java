package AtlasZwierzat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PodgladGatunkuController implements Initializable {
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

    private Gatunek gatunek;
    private EkranGlownyController rodzic;


    @FXML
    private void usun() {
        this.rodzic.usun(this.gatunek);
    }

    /**
     * Ustawia nazwe gatunku jako tytul widoku
     */
    private void ustawTytul() {
        assert tytul != null : "tytul nie jest zainicjalizowany";

        tytul.setText("Gatunek: " + this.gatunek.getNazwa());
    }

    /**
     * Uzupelnia pola formularza danymi gatunku
     */
    private void uzupelnijPola() {
        poleNazwa.setText(gatunek.getNazwa());
        poleDataOdkrycia.setValue(gatunek.getDataOdkrycia());
        poleLiczbaKonczyn.setText(Integer.toString(gatunek.getLiczbaKonczyn()));
        poleImieSlawnegoPrzedstawiciela.setText(gatunek.getImieSlawnegoPrzedstawiciela());
    }

    @FXML
    private void edytuj() {
        String nazwa = poleNazwa.getText();
        LocalDate dataOdkrycia = poleDataOdkrycia.getValue();
        int liczbaKonczyn = Integer.parseInt(poleLiczbaKonczyn.getText());
        String imieSlawnegoPrzedstawieciela = poleImieSlawnegoPrzedstawiciela.getText();
        int id = gatunek.getId();

        rodzic.edytujGatunek(id, nazwa, dataOdkrycia, liczbaKonczyn, imieSlawnegoPrzedstawieciela);
    }

    public void init(Gatunek gatunek, EkranGlownyController rodzic) {
        this.gatunek = gatunek;
        this.rodzic = rodzic;

        ustawTytul();
        uzupelnijPola();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
