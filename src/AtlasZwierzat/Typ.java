/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

/**
 *
 * @author virzen
 */
public class Typ {
    private String nazwa;
    private int szacowanaLiczba;
    private String typowaBudowaCiala;
    private int id;

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getSzacowanaLiczba() {
        return szacowanaLiczba;
    }

    public void setSzacowanaLiczba(int szacowanaLiczba) {
        this.szacowanaLiczba = szacowanaLiczba;
    }

    public String getTypowaBudowaCiala() {
        return typowaBudowaCiala;
    }

    public void setTypowaBudowaCiala(String typowaBudowaCiala) {
        this.typowaBudowaCiala = typowaBudowaCiala;
    }

    public Typ(String nazwa, int szacowanaLiczba, String typowaBudowaCiala) {
        this.nazwa = nazwa;
        this.szacowanaLiczba = szacowanaLiczba;
        this.typowaBudowaCiala = typowaBudowaCiala;
        this.id = GeneratorId.getNewId();
    }
}
