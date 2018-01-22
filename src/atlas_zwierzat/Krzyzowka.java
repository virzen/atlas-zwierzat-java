/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlas_zwierzat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author virzen
 */
public class Krzyzowka extends Gatunek {
    private List<Gatunek> gatunki = new ArrayList<>();

    public Krzyzowka(String nazwa, Date dataOdkrycia, int liczbaKonczyn, String imieSlawnegoPrzedstawiciela, Rodzina rodzina, List<Gatunek> gatunki) {
        super(nazwa, dataOdkrycia, liczbaKonczyn, imieSlawnegoPrzedstawiciela, rodzina);
        this.gatunki = gatunki;
    }

    public List<Gatunek> getGatunki() {
        return gatunki;
    }

    public void setGatunki(List<Gatunek> gatunki) {
        this.gatunki = gatunki;
    }
    
    public List<Rodzina> getRodziny() {
        List<Rodzina> rodziny = new ArrayList<>();
        
        for (Gatunek gatunek : this.gatunki) {
            rodziny.add(gatunek.getRodzina());
        }
        
        return rodziny;
    }
}
