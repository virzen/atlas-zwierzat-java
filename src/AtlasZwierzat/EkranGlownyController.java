/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author virzen
 */
public class EkranGlownyController implements Initializable, Observer {
    @FXML 
    private TreeView tree; 
    @FXML 
    private TextField textField;
        
    private final TreeItem treeRoot = new TreeItem("Atlas");
    private final TreeItem filteredRoot = new TreeItem("Filtrowane gatunki");
    
    private PodgladAtlasuController podgladAtlasuController;
    private Atlas atlas;

    public PodgladAtlasuController getPodgladAtlasuController() {
        return podgladAtlasuController;
    }

    public void setPodgladAtlasuController(PodgladAtlasuController podgladAtlasuController) {
        this.podgladAtlasuController = podgladAtlasuController;
    }

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
    
    @Override
    public void update(Observable o, Object arg) {
        Zdarzenie zdarzenie = (Zdarzenie) arg;
        
        // TYP
        if (zdarzenie.getTyp() == TypZdarzenia.UTWORZ && zdarzenie.getDane() instanceof Typ) {
            Typ typ = (Typ) zdarzenie.getDane();
            if (this.atlas.znajdzTyp(typ.getNazwa()) != null) {
                this.pokazAlertPorazki("Typ o danej nazwie juz istnieje.");
                return;
            }
            
            this.getAtlas().dodajTyp(typ);
            this.otworzPodgladTypu(typ);
        }
        
        if (zdarzenie.getTyp() == TypZdarzenia.EDYTUJ && zdarzenie.getDane() instanceof EdytowanyTyp) {
            EdytowanyTyp noweDaneTypu = (EdytowanyTyp) zdarzenie.getDane();
            
            Typ oryginalnyTyp = this.atlas.znajdzTyp(noweDaneTypu.getIdTypu());
            
            if (oryginalnyTyp == null) {
                this.pokazAlertPorazki("Nie znaleziono typu o id " + noweDaneTypu.getId() + ".");
                return;
            }
            
            String poprzedniaNazwa = oryginalnyTyp.getNazwa();
            oryginalnyTyp.setNazwa(noweDaneTypu.getNazwa());
            oryginalnyTyp.setSzacowanaLiczba(noweDaneTypu.getSzacowanaLiczba());
            oryginalnyTyp.setTypowaBudowaCiala(noweDaneTypu.getTypowaBudowaCiala());
            
            this.pokazAlertSukcesu("Typ " + poprzedniaNazwa + " zostal zmieniony na " + oryginalnyTyp.getNazwa() + ".");
            this.otworzPodgladTypu(oryginalnyTyp);
        }
        
        if (zdarzenie.getTyp() == TypZdarzenia.USUN && zdarzenie.getDane() instanceof Typ) {
            this.atlas.usunTyp((Typ) zdarzenie.getDane());
            this.otworzPodgladAtlasu();
        }
        
        // RODZINA
        if (zdarzenie.getTyp() == TypZdarzenia.UTWORZ && zdarzenie.getDane() instanceof Rodzina) {
            Rodzina rodzina = (Rodzina) zdarzenie.getDane();
            this.atlas.dodajRodzine(rodzina);
            this.otworzPodgladRodziny(rodzina);
        }
        
        if (zdarzenie.getTyp() == TypZdarzenia.EDYTUJ && zdarzenie.getDane() instanceof EdytowanaRodzina) {
            EdytowanaRodzina noweDaneRodziny = (EdytowanaRodzina) zdarzenie.getDane();
            
            Rodzina oryginalnaRodzina = this.atlas.znajdzRodzine(noweDaneRodziny.getIdRodziny());
            
            if (oryginalnaRodzina == null) {
                this.pokazAlertPorazki("Nie znaleziono rodziny o id " + noweDaneRodziny.getId() + ".");
                return;
            }
            
            String poprzedniaNazwa = oryginalnaRodzina.getNazwa();
            oryginalnaRodzina.setNazwa(noweDaneRodziny.getNazwa());
            oryginalnaRodzina.setCechaCharakterystyczna(noweDaneRodziny.getCechaCharakterystyczna());
            oryginalnaRodzina.setSredniaLiczbaKonczyn(noweDaneRodziny.getSredniaLiczbaKonczyn());
            
            this.otworzPodgladRodziny(oryginalnaRodzina);
        }
                
        if (zdarzenie.getTyp() == TypZdarzenia.USUN && zdarzenie.getDane() instanceof Rodzina) {
            Rodzina rodzina = (Rodzina) zdarzenie.getDane();
            this.atlas.usunRodzine(rodzina);
            this.otworzPodgladTypu(rodzina.getTyp());
        }
        
        // GATUNEK
        if (zdarzenie.getTyp() == TypZdarzenia.UTWORZ && zdarzenie.getDane() instanceof Gatunek) {
            Gatunek gatunek = (Gatunek) zdarzenie.getDane();
            this.atlas.dodajGatunek(gatunek);
            this.otworzPodgladGatunku(gatunek);
        }
        
        // TODO: edytuj gatunek
                
        if (zdarzenie.getTyp() == TypZdarzenia.USUN && zdarzenie.getDane() instanceof Gatunek) {
            Gatunek gatunek = (Gatunek) zdarzenie.getDane();
            this.atlas.usunGatunek(gatunek);
            this.otworzPodgladRodziny(gatunek.getRodzina());
        }
        
        // SERIALIZACJA
        if (zdarzenie.getTyp() == TypZdarzenia.ZAPISZ_ATLAS) {
            this.zapiszAtlas((String) zdarzenie.getDane());
        }
        
        if (zdarzenie.getTyp() == TypZdarzenia.WCZYTAJ_ATLAS) {
            this.wczytajAtlas((String) zdarzenie.getDane());
        }
        
        uzupelnijDrzewo();
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
            this.otworzPodgladTypu(typ);
            return;
        }
        
        Rodzina rodzina = this.atlas.znajdzRodzine(nazwaWybranego);
        if (rodzina != null) {
            this.otworzPodgladRodziny(rodzina);
            return;
        }
        
        Gatunek gatunek = this.atlas.znajdzGatunek(nazwaWybranego);
        if (gatunek != null) {
            this.otworzPodgladGatunku(gatunek);
        }
    }
    
    private void otworzPodgladAtlasu() {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladAtlasuController.class.getResource("PodgladAtlasu.fxml"));
            AnchorPane podgladAtlasu = (AnchorPane) loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();            
            borderPane.setCenter(podgladAtlasu);
        } catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodgladTypu(Typ typ) {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladTypuController.class.getResource("PodgladTypu.fxml"));
            AnchorPane podgladTypu = (AnchorPane) loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();            
            borderPane.setCenter(podgladTypu);
            PodgladTypuController podgladTypuController = (PodgladTypuController) loader.getController();
            podgladTypuController.setTyp(typ);
        } 
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodgladRodziny(Rodzina rodzina) {
        try {
            FXMLLoader loader = new FXMLLoader(PodgladTypuController.class.getResource("PodgladRodziny.fxml"));
            AnchorPane podgladRodziny = (AnchorPane) loader.load();
            BorderPane borderPane = (BorderPane) this.tree.getScene().getRoot();            
            borderPane.setCenter(podgladRodziny);
            PodgladRodzinyController podgladRodzinyController = (PodgladRodzinyController) loader.getController();
            podgladRodzinyController.setRodzina(rodzina);
        } 
        catch (IOException ex) {
            Logger.getLogger(EkranGlownyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void otworzPodgladGatunku(Gatunek gatunek) {
        System.out.print("klikniecie gatunku: " + gatunek.toString() + "\n");
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
    
    private void zapiszAtlas(String nazwaPliku) {
        try {
            SerializerAtlasu.zapisz(this.getAtlas(), nazwaPliku);
        } catch (IOException ex) {
            this.pokazAlertPorazki("Wystapił błąd podczas zapisywania atlasu.", ex.toString());
        }
        
        this.pokazAlertSukcesu("Atlas został zapisany poprawnie.");
    }
    
    private void wczytajAtlas(String nazwaPliku) {
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
