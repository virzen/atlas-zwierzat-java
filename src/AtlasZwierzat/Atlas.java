/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author virzen
 */
public class Atlas {
    private List<Typ> typy = new ArrayList<>();
    private List<Rodzina> rodziny = new ArrayList<>();
    private List<Gatunek> gatunki = new ArrayList<>();
    
    public Atlas() {
        // TO BE REMOVED
        Typ typ = new Typ("Typ 1", 100, "Duża");
        Rodzina rodzina = new Rodzina("Rodzina 1", "Zuchwałość", 10, typ);
        Gatunek gatunek1 = new Gatunek("Gatunek 1", new Date(), 10, "Bonzo", rodzina);
        Gatunek gatunek2 = new Gatunek("Gatunek 2", new Date(), 10, "XDXD", rodzina);
        List<Gatunek> gatunki = new ArrayList<>();
        gatunki.add(gatunek1);        
        gatunki.add(gatunek2);
        Krzyzowka krzyzowka = new Krzyzowka("KRzyzowka 1", new Date(), 10, "Test", rodzina, gatunki);
        
        this.dodajTyp(typ);
        this.dodajRodzine(rodzina);
        this.dodajGatunek(gatunek1);
        this.dodajGatunek(gatunek2);
        this.dodajGatunek(krzyzowka);
        
        System.out.print(this.filtrujGatunki("1").toString());
    }
    
    public List<Typ> getTypy() {
        return typy;
    }

    /**
     *
     * @param typy
     */
    public void setTypy(List<Typ> typy) {
        this.typy = typy;
    }
    
    
    public Typ znajdzTyp(String nazwa) {
        Predicate<Typ> predykat = t -> t.getNazwa().equals(nazwa);
        Typ typ = this.getTypy().stream().filter(predykat).findFirst().orElse(null); 
        
        return typ;
    }

    public List<Rodzina> getRodziny() {
        return rodziny;
    }

    /**
     *
     * @param rodziny
     */
    public void setRodziny(List<Rodzina> rodziny) {
        this.rodziny = rodziny;
    }
    
    
    Rodzina znajdzRodzine(String nazwa) {
        Predicate<Rodzina> predykat = r -> r.getNazwa().equals(nazwa);
        Rodzina rodzina = this.getRodziny().stream().filter(predykat).findFirst().orElse(null); 
        
        return rodzina;
    }
    
    public List<Gatunek> getGatunki() {
        return gatunki;
    }

    /**
     *
     * @param gatunki
     */
    public void setGatunki(List<Gatunek> gatunki) {
        this.gatunki = gatunki;
    }
    
    
    Gatunek znajdzGatunek(String nazwa) {
        Predicate<Gatunek> predykat = g -> g.getNazwa().equals(nazwa);
        Gatunek gatunek = this.getGatunki().stream().filter(predykat).findFirst().orElse(null); 
        
        return gatunek;
    }
    
    /**
     *
     * @param typ
     */
    public void dodajTyp(Typ typ) {
        this.typy.add(typ);
    }
    
    /**
     *
     * @param typ
     */
    public void usunTyp(Typ typ) {
        int index = this.typy.indexOf(typ);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param rodzina
     */
    public void dodajRodzine(Rodzina rodzina) {
        this.rodziny.add(rodzina);
    }
    
    /**
     *
     * @param rodzina
     */
    public void usunRodzine(Rodzina rodzina) {
        int index = this.rodziny.indexOf(rodzina);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param gatunek
     */
    public void dodajGatunek(Gatunek gatunek) {
        this.gatunki.add(gatunek);
    }
    
    /**
     *
     * @param gatunek
     */
    public void usunGatunek(Gatunek gatunek) {
        int index = this.gatunki.indexOf(gatunek);
        
        if (index != -1) {
            typy.remove(index);
        }
    }
    
    /**
     *
     * @param zapytanie
     * @return
     */
    public List<Gatunek> filtrujGatunki(String zapytanie) {
        Predicate<Gatunek> czyZawieraZapytanie = g -> g.getNazwa().toLowerCase().contains(zapytanie.toLowerCase());
        
        List<Gatunek> przefiltrowaneGatunki = this.getGatunki().stream().filter(czyZawieraZapytanie).collect(Collectors.toList());
        
        return przefiltrowaneGatunki;
    }
}
