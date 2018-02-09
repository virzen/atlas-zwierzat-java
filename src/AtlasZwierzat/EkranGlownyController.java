/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author virzen
 */
public class EkranGlownyController implements Initializable {
    @FXML 
    private TreeView tree; 
    @FXML 
    private TextField textField;
        
    private final TreeItem treeRoot = new TreeItem("Atlas");
    private final TreeItem filteredRoot = new TreeItem("Filtrowane gatunki");
    
    private PodgladAtlasuController podgladAtlasuController;
    private Atlas atlas;

    public Atlas getAtlas() {
        return atlas;
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }
    
    @FXML
    private void filtrujGatunki() {
        String tekst = this.textField.getText();
        
        if ("".equals(tekst)) {
            this.tree.setRoot(this.treeRoot);
            return;
        }
        
        this.filteredRoot.setExpanded(false);
        this.filteredRoot.getChildren().clear();
        
        List<Gatunek> gatunki = this.atlas.filtrujGatunki(tekst);
        gatunki.stream()
                .map(g -> new TreeItem(g.getNazwa()))
                .forEachOrdered(t -> {
                    this.filteredRoot.getChildren().add(t);
                });
        
        this.tree.setRoot(this.filteredRoot);
        this.filteredRoot.setExpanded(true);
    }
    
    public void dodaj(Gatunek gatunek) {
        atlas.dodaj(gatunek);
        uzupelnijDrzewo();
        otworzPodglad(gatunek);
    }

    public void dodaj(Krzyzowka krzyzowka) {
        atlas.dodaj(krzyzowka);
        uzupelnijDrzewo();
        otworzPodglad(krzyzowka);
    }

    public void dodaj(Rodzina rodzina) {
        atlas.dodaj(rodzina);
        uzupelnijDrzewo();
        otworzPodglad(rodzina);
    }

    public void dodaj(Typ typ) {
        atlas.dodaj(typ);
        uzupelnijDrzewo();
        otworzPodglad(typ);
    }

    public void edytujGatunek(int id, String nazwa, LocalDate dataOdkrycia, int liczbaKonczyn, String imieSlawnegoPrzedstawiciela) {
        Gatunek gatunek = atlas.znajdzGatunek(id);

        String poprzedniaNazwa = gatunek.getNazwa();

        gatunek.setNazwa(nazwa);
        gatunek.setDataOdkrycia(dataOdkrycia);
        gatunek.setLiczbaKonczyn(liczbaKonczyn);
        gatunek.setImieSlawnegoPrzedstawiciela(imieSlawnegoPrzedstawiciela);

        pokazAlertSukcesu("Gatunek " + poprzedniaNazwa + " zostal zmieniony na " + nazwa + ".");
        otworzPodglad(gatunek);
        uzupelnijDrzewo();
    }

    public void edytujKrzyzowke(int id, String nazwa, LocalDate dataOdkrycia, int liczbaKonczyn, String imieSlawnegoPrzedstawiciela, List<Gatunek> gatunki) {
        Krzyzowka krzyzowka = (Krzyzowka) atlas.znajdzGatunek(id);

        String poprzedniaNazwa = krzyzowka.getNazwa();

        krzyzowka.setNazwa(nazwa);
        krzyzowka.setDataOdkrycia(dataOdkrycia);
        krzyzowka.setLiczbaKonczyn(liczbaKonczyn);
        krzyzowka.setImieSlawnegoPrzedstawiciela(imieSlawnegoPrzedstawiciela);
        krzyzowka.setGatunki(gatunki);

        pokazAlertSukcesu("Krzyzowka " + poprzedniaNazwa + " zostala zmieniony na " + nazwa + ".");
        otworzPodglad(krzyzowka);
        uzupelnijDrzewo();
    }

    public void edytujRodzine(int id, String nazwa, String cechaCharakterystyczna, float sredniaLiczbaKonczyn) {
        Rodzina rodzina = atlas.znajdzRodzine(id);

        String poprzedniaNazwa = rodzina.getNazwa();

        rodzina.setNazwa(nazwa);
        rodzina.setCechaCharakterystyczna(cechaCharakterystyczna);
        rodzina.setSredniaLiczbaKonczyn(sredniaLiczbaKonczyn);

        this.pokazAlertSukcesu("Rodzina " + poprzedniaNazwa + " zostal zmieniony na " + nazwa + ".");
        this.otworzPodglad(rodzina);
        uzupelnijDrzewo();
    }

    public void edytujTyp(int id, String nazwa, int szacowanaLiczba, String typowaBudowaCiala) {
        Typ typ = atlas.znajdzTyp(id);

        String poprzedniaNazwa = typ.getNazwa();
        typ.setNazwa(nazwa);
        typ.setSzacowanaLiczba(szacowanaLiczba);
        typ.setTypowaBudowaCiala(typowaBudowaCiala);

        pokazAlertSukcesu("Typ " + poprzedniaNazwa + " zostal zmieniony na " + nazwa + ".");
        otworzPodglad(typ);
        uzupelnijDrzewo();
    }

    public void usun(Gatunek gatunek) {
        this.atlas.usun(gatunek);
        this.otworzPodglad(gatunek.getRodzina());
        uzupelnijDrzewo();
    }

    public void usun(Krzyzowka krzyzowka) {
        usun((Gatunek) krzyzowka);
    }

    public void usun(Rodzina rodzina) {
        this.atlas.usun(rodzina);
        this.otworzPodglad(rodzina.getTyp());
        uzupelnijDrzewo();
    }

    public void usun(Typ typ) {
        this.atlas.usun(typ);
        this.otworzPodgladAtlasu();
        uzupelnijDrzewo();
    }

    // TODO
    private void wybierzElementDrzewa() {
        System.out.println("webierz element drzewa");
    }

    @FXML
    private void obsluzKlikniecieDrzewa() {
        TreeItem wybrany = (TreeItem) this.tree.getSelectionModel().getSelectedItem();

        if (wybrany == null) return;
        
        if (treeRoot.equals(wybrany)) {
            this.otworzPodgladAtlasu();
            return;
        }
        
        String nazwaWybranego = (String) wybrany.getValue();
        
        Typ typ = this.atlas.znajdzTyp(nazwaWybranego);
        if (typ != null) {
            this.otworzPodglad(typ);
            return;
        }
        
        Rodzina rodzina = this.atlas.znajdzRodzine(nazwaWybranego);
        if (rodzina != null) {
            this.otworzPodglad(rodzina);
            return;
        }

        Krzyzowka krzyzowka = atlas.znajdzKrzyzowke(nazwaWybranego);
        if (krzyzowka != null) {
            this.otworzPodglad(krzyzowka);
            return;
        }
        
        Gatunek gatunek = this.atlas.znajdzGatunek(nazwaWybranego);
        if (gatunek != null) {
            this.otworzPodglad(gatunek);
        }
    }
    
    private void otworzPodgladAtlasu() {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladAtlasuController.class.getResource("PodgladAtlasu.fxml"));
            AnchorPane podgladAtlasu = loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();
            borderPane.setCenter(podgladAtlasu);
            PodgladAtlasuController podgladAtlasuController = loader.getController();
            podgladAtlasuController.init(this);
        } catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodglad(Typ typ) {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladTypuController.class.getResource("PodgladTypu.fxml"));
            AnchorPane podgladTypu = loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();            
            borderPane.setCenter(podgladTypu);
            PodgladTypuController podgladTypuController = loader.getController();
            podgladTypuController.init(typ, this);
        } 
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodglad(Rodzina rodzina) {
        List<Gatunek> gatunkiRodziny = atlas.znajdzGatunkiRodziny(rodzina);

        try {
            FXMLLoader loader = new FXMLLoader(PodgladRodzinyController.class.getResource("PodgladRodziny.fxml"));
            AnchorPane podgladRodziny = loader.load();
            BorderPane borderPane = (BorderPane) tree.getScene().getRoot();
            borderPane.setCenter(podgladRodziny);
            PodgladRodzinyController podgladRodzinyController = loader.getController();
            podgladRodzinyController.init(rodzina, gatunkiRodziny, this);
        }
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodglad(Gatunek gatunek) {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladGatunkuController.class.getResource("PodgladGatunku.fxml"));
            AnchorPane podgladGatunku = loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();
            borderPane.setCenter(podgladGatunku);
            PodgladGatunkuController podgladGatunkuController = loader.getController();
            podgladGatunkuController.init(gatunek, this);
        }
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void otworzPodglad(Krzyzowka krzyzowka) {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladKrzyzowkiController.class.getResource("PodgladKrzyzowki.fxml"));
            AnchorPane podgladKrzyzowki = loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();
            borderPane.setCenter(podgladKrzyzowki);
            PodgladKrzyzowkiController podgladKrzyzowkiController = loader.getController();
            podgladKrzyzowkiController.init(krzyzowka, this);
        }
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void uzupelnijDrzewo() {
        List<Typ> typy = this.atlas.getTypy();
        List<Rodzina> rodziny = this.atlas.getRodziny();
        List<Gatunek> gatunki = this.atlas.getGatunki();
        
        this.treeRoot.getChildren().clear();
        
        for (Typ typ : typy) {
            TreeItem typItem = new TreeItem<>(typ.getNazwa());
            
            rodziny.stream()
                .filter((rodzina) -> (rodzina.getTyp().equals(typ)))
                .map((rodzina) -> {
                    TreeItem rodzinaItem = new TreeItem<>(rodzina.getNazwa());

                    gatunki.stream()
                        .filter((gatunek) -> (gatunek.getRodzina().equals(rodzina)))
                        .forEachOrdered((gatunek) -> {
                        rodzinaItem.getChildren().add(new TreeItem<>(gatunek.getNazwa()));
                    });
                    
                    return rodzinaItem;
                }).forEachOrdered((rodzinaItem) -> {
                    typItem.getChildren().add(rodzinaItem);
                });
            
            this.treeRoot.getChildren().add(typItem);
        }
        
        this.treeRoot.setExpanded(true);
        this.tree.setRoot(this.treeRoot);
    }
    
    public void pokazAlertSukcesu(String tekst) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText(tekst);

        alert.showAndWait();
    }

    public void pokazAlertPorazki(String tekst) {
        this.pokazAlertPorazki(tekst, null);
    }
    
    public void pokazAlertPorazki(String naglowek, String zawartosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bład");
        alert.setHeaderText(naglowek);
        alert.setContentText(zawartosc);

        alert.showAndWait();
    }
    
    public void zapiszAtlas(String nazwaPliku) {
        try {
            SerializerAtlasu.zapisz(this.getAtlas(), nazwaPliku);
        } catch (IOException ex) {
            this.pokazAlertPorazki("Wystapił błąd podczas zapisywania atlasu.", ex.toString());
        }
        
        this.pokazAlertSukcesu("Atlas został zapisany poprawnie.");
    }
    
    public void wczytajAtlas(String nazwaPliku) {
        try {
            this.setAtlas(SerializerAtlasu.wczytaj(nazwaPliku));
        } catch (IOException | ClassNotFoundException ex) {
            this.pokazAlertPorazki("Wczytywanie atlasu zakonczyło się niepowodzeniem.", ex.toString());
        }
        
        uzupelnijDrzewo();
        this.pokazAlertSukcesu("Atlas został wczytany poprawnie.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setAtlas(new Atlas());
        
        uzupelnijDrzewo();
    }

}
