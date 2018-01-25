/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author virzen
 */
public class Gatunek implements Serializable {
    String nazwa;
    Date dataOdkrycia;
    int liczbaKonczyn;
    String imieSlawnegoPrzedstawiciela;
    Rodzina rodzina;
    int id;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDataOdkrycia() {
        return dataOdkrycia;
    }

    public void setDataOdkrycia(Date dataOdkrycia) {
        this.dataOdkrycia = dataOdkrycia;
    }

    public int getLiczbaKonczyn() {
        return liczbaKonczyn;
    }

    public void setLiczbaKonczyn(int liczbaKonczyn) {
        this.liczbaKonczyn = liczbaKonczyn;
    }

    public String getImieSlawnegoPrzedstawiciela() {
        return imieSlawnegoPrzedstawiciela;
    }

    public void setImieSlawnegoPrzedstawiciela(String imieSlawnegoPrzedstawiciela) {
        this.imieSlawnegoPrzedstawiciela = imieSlawnegoPrzedstawiciela;
    }

    public Rodzina getRodzina() {
        return rodzina;
    }

    public void setRodzina(Rodzina rodzina) {
        this.rodzina = rodzina;
    }

    public Gatunek(String nazwa, Date dataOdkrycia, int liczbaKonczyn, String imieSlawnegoPrzedstawiciela, Rodzina rodzina) {
        this.nazwa = nazwa;
        this.dataOdkrycia = dataOdkrycia;
        this.liczbaKonczyn = liczbaKonczyn;
        this.imieSlawnegoPrzedstawiciela = imieSlawnegoPrzedstawiciela;
        this.rodzina = rodzina;
        this.id = GeneratorId.getNewId();
    }
    
}
