/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Krzyzowka extends Gatunek {
    private List<Gatunek> gatunki;

    public Krzyzowka(String nazwa, LocalDate dataOdkrycia, int liczbaKonczyn, String imieSlawnegoPrzedstawiciela, Rodzina rodzina, List<Gatunek> gatunki) {
        super(nazwa, dataOdkrycia, liczbaKonczyn, imieSlawnegoPrzedstawiciela, rodzina);
        this.gatunki = gatunki;
    }

    public List<Gatunek> getGatunki() {
        return gatunki;
    }

    public void setGatunki(List<Gatunek> gatunki) {
        this.gatunki = gatunki;
    }
}
