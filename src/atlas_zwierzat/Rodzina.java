/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlas_zwierzat;

/**
 *
 * @author virzen
 */
public class Rodzina {
    private String nazwa;
    private String cechaCharakterystyczna;
    private float sredniaLiczbaKonczyn;
    private Typ typ;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getCechaCharakterystyczna() {
        return cechaCharakterystyczna;
    }

    public void setCechaCharakterystyczna(String cechaCharakterystyczna) {
        this.cechaCharakterystyczna = cechaCharakterystyczna;
    }

    public float getSredniaLiczbaKonczyn() {
        return sredniaLiczbaKonczyn;
    }

    public void setSredniaLiczbaKonczyn(float sredniaLiczbaKonczyn) {
        this.sredniaLiczbaKonczyn = sredniaLiczbaKonczyn;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public Rodzina(String nazwa, String cechaCharakterystyczna, float sredniaLiczbaKonczyn, Typ typ) {
        this.nazwa = nazwa;
        this.cechaCharakterystyczna = cechaCharakterystyczna;
        this.sredniaLiczbaKonczyn = sredniaLiczbaKonczyn;
        this.typ = typ;
    }
    
}